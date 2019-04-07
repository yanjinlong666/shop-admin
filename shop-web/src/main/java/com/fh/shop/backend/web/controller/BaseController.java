/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:BaseController.java 
 * 包名:com.fh.shop.backend.web.controller
 * 创建日期:2018年12月26日下午5:35:41 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.web.controller;

import com.fh.core.common.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：BaseController    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月26日 下午5:35:41    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月26日 下午5:35:41    
 * 修改备注：       
 * @version </pre>    
 */
public class BaseController {

	protected String getRootPath(HttpServletRequest request) {
		String rootPath = request.getServletContext().getRealPath("/");
		return rootPath;
	}



	protected FileInfo convertFileInfo(MultipartFile productImage) throws IOException {
		FileInfo fileInfo = new FileInfo();
		fileInfo.setFileName(productImage.getOriginalFilename());
		fileInfo.setIs(productImage.getInputStream());
		fileInfo.setSize(productImage.getSize());
		return fileInfo;
	}

	protected List<FileInfo> convertFileInfoList(MultipartFile[] productChildImages) throws IOException {
		List<FileInfo> fileInfos = new ArrayList<>();
		for (MultipartFile productChildImage : productChildImages) {
			if (productChildImage.getSize() > 0) {
				fileInfos.add(convertFileInfo(productChildImage));
			}
		}
		return fileInfos;
	}
	
	
	public void outJson(String json, HttpServletResponse response) {
		PrintWriter writer = null;
		try {
			// 设置响应内容的类型并制定编码格式
			 //response.setContentType("application/json;charset=utf-8");
			 // 通过response获取writer
			 writer = response.getWriter();
			 // 通过writer将字符串响应给客户端【浏览器】
			 writer.write(json);
			 // 清空
			 writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭流
			if (null != writer) {
				writer.close();
				writer = null;
			}
		}
	}

}
