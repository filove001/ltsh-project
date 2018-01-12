//package org.ltsh.common.filter;
//
//
//
//import com.alibaba.dubbo.rpc.*;
//import org.ltsh.common.entity.ApiContext;
//
//
//import org.ltsh.common.util.JsonUtils;
//import org.ltsh.common.util.LogUtils;
//import org.slf4j.MDC;
//
//import java.util.Calendar;
//
///**
// *
// * @描述: Api过滤器.
// * @作者: wason .
// * @创建时间: 2017-1-22,下午4:52:52 .
// * @版本: 1.0 .
// * @param
// */
//public class ConsumerFilter implements Filter {
//	@Override
//	public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
//		for(Object ob:RpcContext.getContext().getArguments()) {
//			if (ob instanceof ApiContext) {
//				ApiContext apiContext = (ApiContext)ob;
//				MDC.put("keep", apiContext.getKeep());
////				apiContext.setKeep(String.valueOf(MDC.get("keep")));
//			}
//		}
//		MDC.put("method", invocation.getMethodName());
//		LogUtils.info("请求地址[{}:{}],请求方法[{}.{}], 请求参数:{}",
//				RpcContext.getContext().getRemoteHost(),
//				RpcContext.getContext().getRemotePort() ,
//				invoker.getInterface().getName(),
//				invocation.getMethodName(), JsonUtils.toJsonLogStr(RpcContext.getContext().getArguments(), JsonUtils.getEncryption()));
//		long startTime = Calendar.getInstance().getTimeInMillis();
//		Result invoke = invoker.invoke(invocation);
//		long endTime = Calendar.getInstance().getTimeInMillis();
//		LogUtils.info("执行时间:{} ms",endTime - startTime);
//		LogUtils.info("返回参数:{}", JsonUtils.toJsonLogStr(invoke.getValue(), JsonUtils.getEncryption()));
////		try {
////			MDC.remove("keep");
////		} catch (Exception e) {
////			LogUtils.error("keep", e);
////		}
//		return invoke;
//	}
//
//}
