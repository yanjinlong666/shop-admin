/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:BrandServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.brand 
 * 创建日期:2018年12月29日上午10:38:36 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.brand;

import java.lang.reflect.Type;
import java.util.List;

import com.fh.core.util.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fh.shop.backend.mapper.brand.IBrandMapper;
import com.fh.shop.backend.po.brand.Brand;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：BrandServiceImpl    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月29日 上午10:38:36    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月29日 上午10:38:36    
 * 修改备注：       
 * @version </pre>    
 */
@Service("brandService")
public class BrandServiceImpl implements IBrandService {
	@Autowired
	private IBrandMapper brandMapper;

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#findBrandList()    
	 */
	@Override
	public List<Brand> findBrandList() {
		String brandListInfo = RedisUtil.get("brandList");
		Gson gson = new Gson();
		if (StringUtils.isEmpty(brandListInfo)) {
			List<Brand> brandList = brandMapper.findBrandList();
			String brandListJson = gson.toJson(brandList);
			RedisUtil.set("brandList", brandListJson);
			return brandList;
		} else {
			Type type = new TypeToken<List<Brand>>() {
			}.getType();
			List<Brand> brandList = gson.fromJson(brandListInfo, type);
			return brandList;
		}
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#addBrand(com.fh.shop.backend.po.brand.Brand)    
	 */
	@Override
	public void addBrand(Brand brand) {
		brandMapper.addBrand(brand);
		RedisUtil.del("brandList");
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.brand.IBrandService#updateBrand(com.fh.shop.backend.po.brand.Brand)    
	 */
	@Override
	public void updateBrand(Brand brand) {
		brandMapper.updateBrand(brand);
		RedisUtil.del("brandList");
	}

}
