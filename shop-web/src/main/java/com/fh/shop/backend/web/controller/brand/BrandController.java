/** 
 * <pre>项目名称:shop-admin-v1 
 * 文件名称:BrandController.java 
 * 包名:com.fh.shop.backend.web.controller.brand
 * 创建日期:2019年1月9日下午4:39:10 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.web.controller.brand;

import java.util.List;

import javax.annotation.Resource;

import com.fh.core.common.ServerResponse;
import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.po.brand.Brand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/** 
 * <pre>项目名称：shop-admin-v1    
 * 类名称：BrandController    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2019年1月9日 下午4:39:10    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2019年1月9日 下午4:39:10    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
	
	private static final Logger LOG = LoggerFactory.getLogger(BrandController.class);

	
	@Resource(name="brandService")
	private IBrandService brandService;
	
	@RequestMapping("list")
	public @ResponseBody
	ServerResponse list() {
		List<Brand> brandList = brandService.findBrandList();
		return ServerResponse.success(brandList);
	}
	
	@RequestMapping("findList")
	public String findList(Integer flag, ModelMap map) {
		List<Brand> brandList = brandService.findBrandList();
		map.put("brandList", brandList);
		if (null != flag) {
			// 跳转到子页面
			return "brand/pagelist";
		} else {
			return "brand/index";
		}
	}
	
	@RequestMapping("updateBrand")
	@ResponseBody
	public ServerResponse updateBrand(Brand brand) {
			brandService.updateBrand(brand);
			return ServerResponse.success();
	}
	
	@RequestMapping("addBrand")
	public @ResponseBody ServerResponse addBrand(Brand brand) {
			brandService.addBrand(brand);
			return ServerResponse.success();
	}
}
