/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:Product.java 
 * 包名:com.fh.shop.backend.po.product 
 * 创建日期:2018年12月23日下午12:14:49 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.po.product;

import java.io.Serializable;
import java.util.Date;

import com.fh.shop.backend.po.Page;
import com.fh.shop.backend.po.brand.Brand;
import org.springframework.format.annotation.DateTimeFormat;

import com.auth0.jwt.internal.com.fasterxml.jackson.annotation.JsonFormat;
import com.fh.shop.backend.po.Page;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：Product    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月23日 下午12:14:49    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月23日 下午12:14:49    
 * 修改备注：       
 * @version </pre>    
 */
public class Product extends Page implements Serializable{

	private static final long serialVersionUID = 5306087749856060545L;
	
	private Integer id;

	private String ids;
	
	private String productName;
	
	private Float productPrice;
	
	private Float minPrice;
	
	private Float maxPrice;
	
	private Brand brand = new Brand();
	
	private Date insertTime;
	
	private Date updateTime;

	private String insertTimeStr;

	private String updateTimeStr;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date minInsertTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date maxInsertTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date minUpdateTime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date maxUpdateTime;

	private String productImagePath;

	public String getInsertTimeStr() {
		return insertTimeStr;
	}

	public void setInsertTimeStr(String insertTimeStr) {
		this.insertTimeStr = insertTimeStr;
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public Date getMinUpdateTime() {
		return minUpdateTime;
	}

	public void setMinUpdateTime(Date minUpdateTime) {
		this.minUpdateTime = minUpdateTime;
	}

	public Date getMaxUpdateTime() {
		return maxUpdateTime;
	}

	public void setMaxUpdateTime(Date maxUpdateTime) {
		this.maxUpdateTime = maxUpdateTime;
	}

	

	public Date getMinInsertTime() {
		return minInsertTime;
	}

	public void setMinInsertTime(Date minInsertTime) {
		this.minInsertTime = minInsertTime;
	}

	public Date getMaxInsertTime() {
		return maxInsertTime;
	}

	public void setMaxInsertTime(Date maxInsertTime) {
		this.maxInsertTime = maxInsertTime;
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Float minPrice) {
		this.minPrice = minPrice;
	}

	public Float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Float productPrice) {
		this.productPrice = productPrice;
	}
	
	
	
	

}
