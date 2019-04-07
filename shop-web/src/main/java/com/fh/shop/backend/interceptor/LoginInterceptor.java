/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:LoginInterceptor.java 
 * 包名:com.fh.shop.backend.interceptor 
 * 创建日期:2019年1月21日下午7:44:34 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.core.util.CookieUtil;
import com.fh.core.util.RedisUtil;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.util.SystemConstant;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：LoginInterceptor    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月21日 下午7:44:34    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月21日 下午7:44:34    
 * 修改备注：       
 * @version </pre>    
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	
	/* (non-Javadoc)    
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)    
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String servletPath = request.getServletPath();
		String requestURI = request.getRequestURI();
		StringBuffer requestURL = request.getRequestURL();
		System.out.println(servletPath+":"+requestURI+":"+requestURL);
		String[] whiteList = SystemConstant.WHITE_LIST_URLS.split(",");

		for (String s : whiteList) {
			if (requestURI.endsWith(s)) {
				return true;
			}
		}

		//Object userInfo = request.getSession().getAttribute(SystemConstant.CURR_USER);
		String fh_id = CookieUtil.readCookie(request, "fh_id");
		if (StringUtils.isEmpty(fh_id)) {
			// 跳转到登录页面
			response.sendRedirect(SystemConstant.LOGIN_URL);
			// 禁止后续访问
			return false;
		}
		String userJson = RedisUtil.get("user:" + fh_id);
		// 续命
		RedisUtil.expire("user:" + fh_id, 30*60);
		Gson gson = new Gson();
		User user = gson.fromJson(userJson, User.class);
		request.getSession().setAttribute(SystemConstant.CURR_USER, user);
		if (!StringUtils.isEmpty(userJson)) {
			// 放行
			return true;
		} else {
			// 跳转到登录页面
			response.sendRedirect(SystemConstant.LOGIN_URL);
			// 禁止后续访问
			return false;
		}
		
	}

}
