/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:IUserMapper.java 
 * 包名:com.fh.shop.backend.mapper.user 
 * 创建日期:2019年1月10日上午10:38:49 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.user;

import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.po.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：IUserMapper    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月10日 上午10:38:49    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月10日 上午10:38:49    
 * 修改备注：       
 * @version </pre>    
 */
public interface IUserMapper {
	
	public User findUserByUserName(String userName);

    void updateLastLoginTime(User userDB);

    void resetLoginCount(Integer id);

    void increaseLoginCount(Integer id);

    void updateLoginErrorInfo(@Param("id") Integer id, @Param("currDate") Date date);

    void updateLoginErrorCount(@Param("id") Integer id, @Param("currDate") Date date);

    void resetLoginErrorCount(Integer id);

    void updateUserStatus(Integer id);

    long findUserListCount(User user);

    List<User> findUserPageList(User user);

    void addUser(User user);

    User findUserById(Integer id);

    void updateUser(User user);

    void batchChangeDept(@Param("idList") List<Integer> ids, @Param("deptId") Integer deptId);

    void deleteUser(Integer id);
}
