/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:UserController.java 
 * 包名:com.fh.shop.backend.web.controller.user
 * 创建日期:2019年1月10日上午10:58:20 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.web.controller.user;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.core.common.ResponseEnum;
import com.fh.core.common.ServerResponse;
import com.fh.core.util.CookieUtil;
import com.fh.core.util.DateUtil;
import com.fh.core.util.MD5Util;
import com.fh.core.util.RedisUtil;
import com.fh.shop.backend.biz.user.IUserService;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.util.*;
import com.fh.shop.backend.vo.UserVo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：UserController    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月10日 上午10:58:20    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月10日 上午10:58:20    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("/user")
public class UserController {
	@Resource(name="userService")
	private IUserService userService;

	@RequestMapping("index")
	public String index() {
		return "user/index";
	}

	@RequestMapping("list")
	@ResponseBody
	public ServerResponse list(User user) {
		return ServerResponse.success(userService.findUserList(user));
	}

	@RequestMapping("addUser")
	@ResponseBody
	public ServerResponse addUser(User user) {
		userService.addUser(user);
		return ServerResponse.success();
	}

	@RequestMapping("findUser")
	@ResponseBody
	public ServerResponse findUser(Integer id) {
		UserVo user = userService.findUserById(id);
		return ServerResponse.success(user);
	}

	@RequestMapping("deleteUser")
	@ResponseBody
	public ServerResponse deleteUser(Integer id) {
		userService.deleteUser(id);
		return ServerResponse.success();
	}

	@RequestMapping("updateUser")
	@ResponseBody
	public ServerResponse updateUser(User user) {
		userService.updateUser(user);
		return ServerResponse.success();
	}

	@RequestMapping("batchChangeDept")
	@ResponseBody
	public ServerResponse batchChangeDept(@RequestParam("ids[]") List<Integer> ids, Integer deptId) {
		userService.batchChangeDept(ids, deptId);
		return ServerResponse.success();
	}

	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		String fh_id = CookieUtil.readCookie(request, "fh_id");
		if (StringUtils.isNotEmpty(fh_id)) {
			// 删除redis
			RedisUtil.del("user:"+fh_id);
			// 删cookie
			CookieUtil.deleteCookie(response, "fh_id", "admin.fh.com");
		}
		request.getSession().invalidate();
		return "redirect:/login.jsp";
	}
	
	@RequestMapping("login")
	@ResponseBody
	public ServerResponse login(User user, HttpServletRequest request) {
		User userDB = null;
		// 记录本次登录的时间
		Date currDate = new Date();
		String fh_id = CookieUtil.readCookie(request, "fh_id");
		// 当前的时间
		Date currDay = DateUtil.str2date(DateUtil.date2Str(currDate, DateUtil.FULL_DATE), DateUtil.Y_M_D);
		try {
			String userName = user.getUserName();
			String userPwd = user.getUserPwd();
			String imgCode = user.getImgCode();
			if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(userPwd) || StringUtils.isEmpty(imgCode)) {
				return ServerResponse.error(ResponseEnum.USERNAME_USERPWD_IMGCODE_EMPTY);
			}
			// 进行验证码的对比
			//String sessionImgCode = (String) request.getSession().getAttribute(SystemConstant.IMGCODE);

			String sessionImgCode = RedisUtil.get("code:" + fh_id);
			if (!imgCode.equals(sessionImgCode)) {
				return ServerResponse.error(ResponseEnum.IMGCODE_ERROR);
			}
			userDB = userService.findUserByUserName(userName);
			if (userDB == null) {
				return ServerResponse.error(ResponseEnum.USERNAME_ERROR);
			}
			int status = userDB.getStatus();
			if (status == SystemConstant.USER_LOCK_STATUS) {
				return ServerResponse.error(ResponseEnum.USERLOCK_ERROR);
			}
			String md5Password = MD5Util.getStringMD5(userPwd+userDB.getSalt());
			if (!md5Password.equals(userDB.getUserPwd())) {
				Date loginErrorTime = userDB.getLoginErrorTime();
				if (null == loginErrorTime) {
					// 如果loginErrorTime为null，则证明第一次错误，需要记录错误的时间为当前时间并使其错误次数为1
					userService.updateLoginErrorInfo(userDB.getId());
				} else {
					// 如果不为null,则判断是否是同一天
					Date loginErrorDay = DateUtil.str2date(DateUtil.date2Str(loginErrorTime, DateUtil.Y_M_D), DateUtil.Y_M_D);
					if (currDay.after(loginErrorDay)) {
						// 不是同一天则次数重置为1，并且更新错误的记录时间
						userService.updateLoginErrorInfo(userDB.getId());
					} else {
						// 是同一天则次数加1，并且更新错误的记录时间
						if (userDB.getLoginErrorCount() == SystemConstant.LOCK_COUNT-1) {
							// 同一天内登录的错误次数已经是2，本次又出现错误，则证是第三次，需要锁定账号
							userService.updateUserStatus(userDB.getId());
						}
						userService.updateLoginErrorCount(userDB.getId());
					}
				}
				return ServerResponse.error(ResponseEnum.USERPWD_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ServerResponse.error();
		}
		// 登录成功，给凭证；给的凭证是啥，凭证存到哪
		// 在后续可以从session中获取信息
		//request.getSession().setAttribute(SystemConstant.CURR_USER, userDB);
		// 删除redis中的验证码
		RedisUtil.del("code:"+fh_id);
		// 安全
		userDB.setUserPwd("");
		userDB.setSalt("");
		Gson gson = new Gson();
		String userDBJson = gson.toJson(userDB);
		RedisUtil.set("user:"+fh_id, userDBJson);
		RedisUtil.expire("user:"+fh_id, 30*60);
		// 登录成功将错误次数重置为0
		userService.resetLoginErrorCount(userDB.getId());
		user.setId(userDB.getId());
		user.setLastLoginTime(currDate);
		userService.updateLastLoginTime(user);
		// 更新登录次数
		// 上次登录时间
		Date lastLoginTime = userDB.getLastLoginTime();
		if (lastLoginTime == null) {
			// 证明是第一次登录
			userService.resetLoginCount(userDB.getId());
			return ServerResponse.success();
		}
		Date lastLoginDay = DateUtil.str2date(DateUtil.date2Str(lastLoginTime, DateUtil.FULL_DATE), DateUtil.Y_M_D);
		if (currDay.after(lastLoginDay)) {
			// 重置为1
			userService.resetLoginCount(userDB.getId());
		} else {
			// 加1
			userService.increaseLoginCount(userDB.getId());
		}
		return ServerResponse.success();
	}
}
