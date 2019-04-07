/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:IUserService.java 
 * 包名:com.fh.shop.backend.biz.user 
 * 创建日期:2019年1月10日上午10:37:10 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.user;

import com.fh.core.common.DataTableResult;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.po.user.User;
import com.fh.shop.backend.vo.UserVo;

import java.util.List;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：IUserService    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月10日 上午10:37:10    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月10日 上午10:37:10    
 * 修改备注：       
 * @version </pre>    
 */
public interface IUserService {

	public User findUserByUserName(String userName);

    void updateLastLoginTime(User userDB);

    void resetLoginCount(Integer id);

    void increaseLoginCount(Integer id);

    void updateLoginErrorInfo(Integer id);

    void updateLoginErrorCount(Integer id);

    void resetLoginErrorCount(Integer id);

    void updateUserStatus(Integer id);

    public DataTableResult findUserList(User user);


    void addUser(User user);

    UserVo findUserById(Integer id);

    void updateUser(User user);

    void batchChangeDept(List<Integer> ids, Integer deptId);

    void deleteUser(Integer id);
}
