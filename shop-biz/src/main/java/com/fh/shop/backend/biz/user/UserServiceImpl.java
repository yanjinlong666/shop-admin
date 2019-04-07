/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:UserServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.user 
 * 创建日期:2019年1月10日上午10:38:12 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.user;

import com.fh.core.common.DataTableResult;
import com.fh.core.util.COSUtil;
import com.fh.core.util.DateUtil;
import com.fh.core.util.MD5Util;
import com.fh.shop.backend.po.DataTablePage;
import com.fh.shop.backend.po.user.User;

import com.fh.shop.backend.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.user.IUserMapper;
import com.fh.shop.backend.po.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：UserServiceImpl    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月10日 上午10:38:12    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月10日 上午10:38:12    
 * 修改备注：       
 * @version </pre>    
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IUserMapper userMapper;

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.user.IUserService#findUserByUserName(java.lang.String)    
	 */
	@Override
	public User findUserByUserName(String userName) {
		User user = userMapper.findUserByUserName(userName);
		return user;
	}

    @Override
    public void updateLastLoginTime(User userDB) {
		userMapper.updateLastLoginTime(userDB);
    }

	@Override
	public void resetLoginCount(Integer id) {
		userMapper.resetLoginCount(id);
	}

	@Override
	public void increaseLoginCount(Integer id) {
		userMapper.increaseLoginCount(id);
	}

    @Override
    public void updateLoginErrorInfo(Integer id) {
		userMapper.updateLoginErrorInfo(id, new Date());
    }

	@Override
	public void updateLoginErrorCount(Integer id) {
		userMapper.updateLoginErrorCount(id, new Date());
	}

	@Override
	public void resetLoginErrorCount(Integer id) {
		userMapper.resetLoginErrorCount(id);
	}

    @Override
    public void updateUserStatus(Integer id) {
		userMapper.updateUserStatus(id);
    }

    @Override
    public DataTableResult findUserList(User user) {
		// 填充排序信息

		// 转换部门列表
		String deptIds = user.getDeptIds();
		if (StringUtils.isNotEmpty(deptIds)) {
			String[] deptIdArr = deptIds.split(",");
			List<Integer> idList = new ArrayList<>();
			for (String s : deptIdArr) {
				idList.add(Integer.parseInt(s));
			}
			user.setDeptIdList(idList);
		}
		// 获取总条数
		long totalCount = userMapper.findUserListCount(user);
		// 获取分页列表
		List<User> userList = userMapper.findUserPageList(user);
		// po转vo
		List<UserVo> userVoList = new ArrayList<>();
		for (User userInfo : userList) {
			UserVo userVo = new UserVo();
			userVo.setId(userInfo.getId());
			userVo.setUserName(userInfo.getUserName());
			userVo.setRealName(userInfo.getRealName());
			userVo.setDeptName(userInfo.getDeptName());
			userVo.setBirthday(DateUtil.date2Str(userInfo.getBirthday(), DateUtil.Y_M_D));
			userVo.setSex(userInfo.getSex());
			userVo.setSalary(userInfo.getSalary());
			userVo.setHeaderPath(userInfo.getHeaderPath());
			userVoList.add(userVo);
		}
		// 组装result
		DataTableResult dataTableResult = DataTableResult.build(user.getDraw(), totalCount, totalCount, userVoList);
		return dataTableResult;
    }

	@Override
	public void addUser(User user) {
		// 生成salt
		String salt = UUID.randomUUID().toString();
		user.setSalt(salt);
		// 加密密码
		String newPassword = MD5Util.getStringMD5(MD5Util.getStringMD5(user.getUserPwd()) + salt);
		// 重新设置
		user.setUserPwd(newPassword);
		userMapper.addUser(user);
	}

    @Override
    public UserVo findUserById(Integer id) {
		User user = userMapper.findUserById(id);
		UserVo userVo = wrapperUserVo(user);
		return userVo;
    }

	@Override
	public void updateUser(User user) {
		// 判断是否上传了新图片
		String headerPath = user.getHeaderPath();
		String oldHeaderPath = user.getOldHeaderPath();
		if (StringUtils.isNotEmpty(headerPath)) {
			// 上传了新图片，删除cos上的旧图片
			COSUtil.delete(oldHeaderPath);
		} else {
			user.setHeaderPath(oldHeaderPath);
		}
		userMapper.updateUser(user);
	}

	@Override
	public void batchChangeDept(List<Integer> ids, Integer deptId) {
		userMapper.batchChangeDept(ids, deptId);
	}

    @Override
    public void deleteUser(Integer id) {
        // 查用户对应的头像
		User userInfo = userMapper.findUserById(id);
		// 删除头像
		String headerPath = userInfo.getHeaderPath();
		COSUtil.delete(headerPath);
		// 删除用户
		userMapper.deleteUser(id);
    }

    private UserVo wrapperUserVo(User user) {
		UserVo userVo = new UserVo();
		userVo.setId(user.getId());
		userVo.setUserName(user.getUserName());
		userVo.setBirthday(DateUtil.date2Str(user.getBirthday(), DateUtil.Y_M_D));
		userVo.setDeptName(user.getDeptName());
		userVo.setRealName(user.getRealName());
		userVo.setHeaderPath(user.getHeaderPath());
		userVo.setDeptId(user.getDeptId());
		userVo.setSalary(user.getSalary());
		return userVo;
	}

}
