/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:LogAspect.java 
 * 包名:com.fh.shop.backend.aspect 
 * 创建日期:2019年1月21日上午11:09:16 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.aspect;

import com.fh.core.annotation.Log;
import com.fh.core.common.ServerResponse;
import com.fh.core.common.WebContext;
import com.fh.shop.backend.biz.log.ILogService;
import com.fh.shop.backend.po.log.LogInfo;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.util.SystemConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：LogAspect    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月21日 上午11:09:16    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月21日 上午11:09:16    
 * 修改备注：       
 * @version </pre>    
 */
public class LogAspect {

	@Resource(name="logService")
	private ILogService logService;
	
	private static final Logger LOG = LoggerFactory.getLogger(LogAspect.class);
	
	public Object doLog(ProceedingJoinPoint pjp) {
		// 动态获取类名
		String className = pjp.getTarget().getClass().getCanonicalName();
		// 动态获取方法名
		String methodName = pjp.getSignature().getName();
		// 动态获取返回值
		MethodSignature ms = (MethodSignature) pjp.getSignature();
		String methodReturnType = ms.getReturnType().getSimpleName();
		Method method = ms.getMethod();
		String content = "";
		// 判断方法上是否有指定的自定义注解
		if (method.isAnnotationPresent(Log.class)) {
			// 获取指定的自定义注解
			Log logAnnotation = method.getAnnotation(Log.class);
			// 获取内容
			content = logAnnotation.value();
		}
		//LOG.info("进入{}的{}()", className, methodName);
		Object result = null;
		User userInfo = null;



		LogInfo logInfo = new LogInfo();
		try {
			// 执行真正的方法
			// 方法执行后的返回值
			long start = System.currentTimeMillis(); // 返回当前的毫秒值
			result = pjp.proceed();
			long end = System.currentTimeMillis(); // 返回当前毫秒值
			// 获取用户信息[login方法执行之后，才会将用户信息存入session中]
			userInfo = (User) WebContext.getRequest().getSession().getAttribute("user");
			LOG.info("{}执行{}的{}()成功", userInfo.getUserName(), className, methodName);
			logInfo.setUserName(userInfo.getUserName());
			logInfo.setInfo("执行"+className+"的"+methodName+"()成功");
			logInfo.setCreateTime(new Date());
			logInfo.setExecuteTime((int) (end - start));
			logInfo.setStatus(SystemConstant.STATUS_SUCCESS);
			logInfo.setContent(content);
			logService.addLog(logInfo);
		} catch (Throwable e) {
			// 在控制台打印异常详细信息
			e.printStackTrace();
			userInfo = (User) WebContext.getRequest().getSession().getAttribute("user");
			if (null != userInfo) {
				LOG.error("{}执行{}的{}()失败:{}", userInfo.getUserName(), className, methodName, e.getMessage());
				logInfo.setUserName(userInfo.getUserName());
				logInfo.setInfo("执行"+className+"的"+methodName+"()失败:"+e.getMessage());
				logInfo.setCreateTime(new Date());
				logInfo.setExecuteTime(SystemConstant.EXECUTE_TIME_ERROR);
				logInfo.setStatus(SystemConstant.STATUS_ERROR);
				logInfo.setContent(content+"失败了");
				// 记录日志
				logService.addLog(logInfo);
				if (methodReturnType.equalsIgnoreCase("string")) {
					return "/error";
				} else {
					return ServerResponse.error();
				}
			}
		}
		return result;
	}
}
