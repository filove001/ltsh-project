package org.ltsh.common.filter;


import com.alibaba.dubbo.rpc.*;
import org.ltsh.common.entity.ApiContext;
import org.ltsh.common.utils.JsonUtil;
import org.ltsh.common.utils.LogUtils;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

/**
 * 
 * @描述: Api过滤器.
 * @作者: wason .
 * @创建时间: 2017-1-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param
 */
public class ProviderFilter implements Filter{
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		//判断是否符合授权
		for(Object ob:RpcContext.getContext().getArguments()){
			if(ob instanceof ApiContext){
				ApiContext apiContext = (ApiContext)ob;
				try {
					if(!StringUtils.isEmpty(apiContext.getKeep())) {
						MDC.put("keep", apiContext.getKeep());
					}
				} catch (Exception e) {
					LogUtils.error("keep", e);
				}

				LogUtils.info("请求地址[{}:{}],请求接口[{}.{}], 请求参数:{}",
						RpcContext.getContext().getRemoteHost(),
						RpcContext.getContext().getRemotePort() ,
						invocation.getAttachment("path"),
						invocation.getMethodName(), JsonUtil.toJsonLogStr(RpcContext.getContext().getArguments(), JsonUtil.getEncryption()));
				try {
					Result invoke = invoker.invoke(invocation);
					Object object = invoke.getValue();
					if(object==null){
						LogUtils.info("返回参数:null");
						return invoke;
					}
					LogUtils.info("返回参数:{}",JsonUtil.toJsonLogStr(object, JsonUtil.getEncryption()));
					return invoke;
				} catch(RpcException e) {
					LogUtils.error("调用接口异常", e);
					throw e;
				} finally {
//						MDC.remove("keep");
				}

			}else{//没有获取上下文
				throw new RpcException("Api没有获取到上下文，授权不通过"); 
			}
		} 
		return new RpcResult();
	}
}
