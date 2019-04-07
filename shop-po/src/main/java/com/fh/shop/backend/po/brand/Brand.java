/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:Brand.java 
 * 包名:com.fh.shop.backend.po.brand 
 * 创建日期:2018年12月29日上午10:29:59 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.brand;

import java.io.Serializable;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：Brand    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月29日 上午10:29:59    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月29日 上午10:29:59    
 * 修改备注：       
 * @version </pre>    
 */
public class Brand implements Serializable{

	

	private static final long serialVersionUID = 4919074125680828518L;

	private Integer id;
	
	private String brandName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	 
}
