/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:User.java 
 * 包名:com.fh.shop.backend.po.user 
 * 创建日期:2019年1月10日上午10:14:20 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.user;

import com.fh.shop.backend.po.DataTablePage;
import com.fh.shop.backend.po.dept.Dept;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：User    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月10日 上午10:14:20    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月10日 上午10:14:20    
 * 修改备注：       
 * @version </pre>    
 */
public class User extends DataTablePage implements Serializable{
	
	private static final long serialVersionUID = 7062684182568492318L;

	private Integer id;
	
	private String userName;



	private String userPwd;

	private String imgCode;

	private String salt;

	private Date lastLoginTime;

	private int loginCount;

	private int status;

	private Date loginErrorTime;

	private int loginErrorCount;

	private String headerPath;

	private String oldHeaderPath;

	private int salary;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	private String realName;

	private int sex;

	private int deptId;

	private String deptName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date minBirthday;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date maxBirthday;

	private Integer minSalary;

	private Integer maxSalary;

	private String deptIds;

	private List<Integer> deptIdList = new ArrayList<>();

	public String getOldHeaderPath() {
		return oldHeaderPath;
	}

	public void setOldHeaderPath(String oldHeaderPath) {
		this.oldHeaderPath = oldHeaderPath;
	}

	public String getHeaderPath() {
		return headerPath;
	}

	public void setHeaderPath(String headerPath) {
		this.headerPath = headerPath;
	}

	public List<Integer> getDeptIdList() {
		return deptIdList;
	}

	public void setDeptIdList(List<Integer> deptIdList) {
		this.deptIdList = deptIdList;
	}

	public Date getMinBirthday() {
		return minBirthday;
	}

	public void setMinBirthday(Date minBirthday) {
		this.minBirthday = minBirthday;
	}

	public Date getMaxBirthday() {
		return maxBirthday;
	}

	public void setMaxBirthday(Date maxBirthday) {
		this.maxBirthday = maxBirthday;
	}

	public Integer getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(Integer minSalary) {
		this.minSalary = minSalary;
	}

	public Integer getMaxSalary() {
		return maxSalary;
	}

	public void setMaxSalary(Integer maxSalary) {
		this.maxSalary = maxSalary;
	}

	public String getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(String deptIds) {
		this.deptIds = deptIds;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getLoginErrorTime() {
		return loginErrorTime;
	}

	public void setLoginErrorTime(Date loginErrorTime) {
		this.loginErrorTime = loginErrorTime;
	}

	public int getLoginErrorCount() {
		return loginErrorCount;
	}

	public void setLoginErrorCount(int loginErrorCount) {
		this.loginErrorCount = loginErrorCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	
}
