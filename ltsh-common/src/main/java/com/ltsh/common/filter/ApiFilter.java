package com.ltsh.common.filter;


import com.alibaba.dubbo.rpc.*;
import com.ltsh.common.entity.ApiContext;


import com.ltsh.common.entity.AppContext;
import com.ltsh.common.util.JsonUtils;
import com.ltsh.common.util.LogUtils;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.Calendar;

/**
 * 
 * @描述: Api过滤器.
 * @作者: wason .
 * @创建时间: 2017-1-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param
 */
public class ApiFilter implements Filter{
	@Override
	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
		MDC.put("keep", com.ltsh.common.util.StringUtils.getRandomString(32));
		boolean isrn = false;
		//判断是否符合授权
		for(Object ob:RpcContext.getContext().getArguments()){
			if(ob instanceof AppContext){
				AppContext appContext = (AppContext)ob;
				try {
					if(!StringUtils.isEmpty(appContext.getKeep())) {
						MDC.put("keep", appContext.getKeep());
					}
				} catch (Exception e) {
					LogUtils.error("keep", e);
				}
				isrn = true;
			}
		}
		if(!isrn) {
			throw new RpcException("请求接口参数不正确");
		}
		LogUtils.info("请求地址[{}:{}],请求接口[{}.{}], 请求参数:{}",
				RpcContext.getContext().getRemoteHost(),
				RpcContext.getContext().getRemotePort() ,
				invocation.getAttachment("path"),
				invocation.getMethodName(), JsonUtils.toJsonLogStr(RpcContext.getContext().getArguments(), JsonUtils.getEncryption()));
		try {
			long startTime = Calendar.getInstance().getTimeInMillis();
			Result invoke = invoker.invoke(invocation);
			Object object = invoke.getValue();
			long endTime = Calendar.getInstance().getTimeInMillis();
			LogUtils.info("执行时间:{}", (endTime - startTime) + " ms");
			if(object==null){
				LogUtils.info("返回参数:null");
				return invoke;
			}

			LogUtils.info("返回参数:{}", JsonUtils.toJsonLogStr(object, JsonUtils.getEncryption()));
			return invoke;
		} catch(RpcException e) {
			LogUtils.error("调用接口异常", e);
			throw e;
		} finally {
//			MDC.remove("keep");
		}
	}
}
