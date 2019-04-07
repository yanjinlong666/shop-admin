/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:IBrandMapper.java 
 * 包名:com.fh.shop.backend.mapper.brand 
 * 创建日期:2018年12月29日上午10:39:08 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.brand;

import java.util.List;

import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：IBrandMapper    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月29日 上午10:39:08    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月29日 上午10:39:08    
 * 修改备注：       
 * @version </pre>    
 */
public interface IBrandMapper {
	
	public List<Brand> findBrandList();

	/** <pre>addBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2019年1月15日 下午5:54:19    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2019年1月15日 下午5:54:19    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void addBrand(Brand brand);

	/** <pre>updateBrand(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2019年1月16日 上午11:44:52    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2019年1月16日 上午11:44:52    
	 * 修改备注： 
	 * @param brand</pre>    
	 */
	public void updateBrand(Brand brand);

}
