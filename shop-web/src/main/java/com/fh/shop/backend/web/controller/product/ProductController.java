/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:ProductController.java 
 * 包名:com.fh.shop.backend.web.controller.product
 * 创建日期:2018年12月23日下午12:19:16 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.web.controller.product;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fh.core.annotation.Log;
import com.fh.core.common.DataTableResult;
import com.fh.core.common.FileInfo;
import com.fh.core.common.ServerResponse;
import com.fh.core.util.FileUtil;
import com.fh.shop.backend.biz.product.IProductImageService;
import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.web.controller.BaseController;
import com.fh.shop.backend.po.brand.Brand;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.util.SystemConstant;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import net.sf.json.JSONObject;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：ProductController    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月23日 下午12:19:16    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月23日 下午12:19:16    
 * 修改备注：       
 * @version </pre>    
 */
// <bean id="productController" class="com.fh.shop.backend.web.controller.product.ProductController">
// </bean>
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
	
	@Resource(name="productService")
	private IProductService productService;
	@Resource(name="brandService")
	private IBrandService brandService;
	@Resource(name="productImageService")
	private IProductImageService productImageService;

	@RequestMapping("toList")
	public String toList() {
		return "product/list";
	}


	@RequestMapping("exportExcel")
	public void exportExcel(Product product, HttpServletResponse response) {
		// 查询符合条件的商品数据[目前的需求导出的数据是符合条件的全部数据，不需要分页]
		List<Product> productList = productService.findProductList(product);
		// 将其商品转换为excel格式即workbook
		XSSFWorkbook workbook = buildWorkBook(productList);
		// 下载
		FileUtil.excelDownload(workbook, response);
	}

	private XSSFWorkbook buildWorkBook(List<Product> productList) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 构建sheet
		buildSheet(productList, workbook);
		return workbook;
}

	private void buildSheet(List<Product> productList, XSSFWorkbook workbook) {
		XSSFSheet sheet = workbook.createSheet("产品表");
		// 构建大标题
		buildTitle(sheet, workbook);
		// 构建表头
		buildHeader(sheet, workbook);
		// 构建表体
		buildBody(productList, sheet, workbook);
	}

	private XSSFCellStyle buildTitleStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeightInPoints((short) 28);
		font.setColor(HSSFColor.RED.index);
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return cellStyle;
	}

	private void buildTitle(XSSFSheet sheet, XSSFWorkbook workbook) {
		XSSFRow row = sheet.createRow(3);
		XSSFCell cell = row.createCell(7);
		cell.setCellValue("商品列表");
		CellRangeAddress cellRangeAddress = new CellRangeAddress(3, 5, 7, 10);
		sheet.addMergedRegion(cellRangeAddress);
		// 构建大标题的样式
		XSSFCellStyle cellStyle = buildTitleStyle(workbook);
		// 设置样式
		cell.setCellStyle(cellStyle);
	}

	private void buildBody(List<Product> productList, XSSFSheet sheet, XSSFWorkbook wb) {
		int startRow = 7;
		int startCol = 7;
		XSSFCellStyle dateStyle = wb.createCellStyle();
		dateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

		XSSFCellStyle dateStyleColor = wb.createCellStyle();
		dateStyleColor.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		dateStyleColor.setFillForegroundColor(HSSFColor.RED.index);
		dateStyleColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		XSSFCellStyle rowBackColor = wb.createCellStyle();
		rowBackColor.setFillForegroundColor(HSSFColor.RED.index);
		rowBackColor.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		for (int i = 0; i < productList.size(); i++) {
			Product productInfo = productList.get(i);
			XSSFRow row = sheet.createRow(i+startRow);
			XSSFCell productNameCell = row.createCell(startCol);
			productNameCell.setCellValue(productInfo.getProductName());
			XSSFCell priceCell = row.createCell(startCol+1);
			Float productPrice = productInfo.getProductPrice();
			priceCell.setCellValue(productPrice);
			XSSFCell brandNameCell = row.createCell(startCol+2);
			brandNameCell.setCellValue(productInfo.getBrand().getBrandName());
			XSSFCell insertDateCell = row.createCell(startCol + 3);
			insertDateCell.setCellValue(productInfo.getInsertTime());
			if (productPrice < 100) {
				productNameCell.setCellStyle(rowBackColor);
				priceCell.setCellStyle(rowBackColor);
				brandNameCell.setCellStyle(rowBackColor);
				insertDateCell.setCellStyle(dateStyleColor);
			} else {
				insertDateCell.setCellStyle(dateStyle);
			}



		}
	}

	private void buildHeader(XSSFSheet sheet, XSSFWorkbook wb) {
		XSSFCellStyle cellStyle = buildHeaderStyle(wb);
		String[] headers = {"产品名", "产品价格", "品牌名", "录入时间"};
		XSSFRow title = sheet.createRow(6)	;
		for (int i = 0; i < headers.length; i++) {
			String header = headers[i];
			XSSFCell productNameTitle = title.createCell(i+7);
			productNameTitle.setCellValue(header);
			productNameTitle.setCellStyle(cellStyle);
		}
	}

	private XSSFCellStyle buildHeaderStyle(XSSFWorkbook wb) {
		XSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		XSSFFont font = wb.createFont();
		font.setBold(true);
		cellStyle.setFont(font);
		return cellStyle;
	}

	@RequestMapping("viewChildImages")
	public String viewChildImages(Integer id, ModelMap map) {
		List<ProductImage> productImageList = productImageService.findProductImageList(id);
		map.put("productImageList", productImageList);
		return "product/childImages";
	}

	@RequestMapping("exportExcelByBrand")
	public void exportExcelByBrand(Product product, HttpServletResponse response) {
		// 查询符合条件的数据
		List<Brand> brandList = brandService.findBrandList();
		XSSFWorkbook wb = new XSSFWorkbook();
		Integer brandId = product.getBrand().getId();
		// 将数据转换为指定的格式【workbook】
		for (Brand brand : brandList) {
			List<Product> productList = null;
			if (brandId == -1) {
				// 核心代码
				product.getBrand().setId(brand.getId());
				// 获取各个品牌对应的产品列表
				productList = productService.findProductList(product);
			} else {
				// 获取各个品牌对应的产品列表
				if (brand.getId() ==  product.getBrand().getId()) {
					productList = productService.findProductList(product);
				} else {
					productList = new ArrayList<>();
				}

			}

			// 创建品牌sheet
			XSSFSheet sheet = wb.createSheet(brand.getBrandName()+"【"+productList.size()+"】");
			// sheet的内容
			XSSFRow title = sheet.createRow(0);
			XSSFCell productNameTitle = title.createCell(0);
			productNameTitle.setCellValue("产品名");
			XSSFCell productPriceTitle = title.createCell(1);
			productPriceTitle.setCellValue("产品价格");
			XSSFCell brandNameTitle = title.createCell(2);
			brandNameTitle.setCellValue("品牌名");
			for (int i = 0; i < productList.size(); i++) {
				Product productInfo = productList.get(i);
				XSSFRow row = sheet.createRow(i+1);
				XSSFCell productNameCell = row.createCell(0);
				productNameCell.setCellValue(productInfo.getProductName());
				XSSFCell priceCell = row.createCell(1);
				priceCell.setCellValue(productInfo.getProductPrice());
				XSSFCell brandNameCell = row.createCell(2);
				brandNameCell.setCellValue(productInfo.getBrand().getBrandName());
			}
		}
		// 下载
		FileUtil.excelDownload(wb, response);
	}

	
	@RequestMapping("toAdd")
	public String toAddProduct(ModelMap map) {
		return "product/add";
	}
	
	@RequestMapping("add")
	@Log("添加产品")
	public String add(Product product, @RequestParam MultipartFile productImage, @RequestParam MultipartFile[] productChildImages, HttpServletRequest request, String productName, Float productPrice) {
		// ========================上传文件[图片]=======================
		// 将本地硬盘上的文件上传到服务器的硬盘上，这个 网络 上传的过程是框架帮我们完成的
		// 文件的拷贝【将已经上传到服务器硬盘上的临时文件拷贝到指定的目录下】
		// 图片通常应该放在项目的web文件夹【编译部署后的】
		try {
			InputStream inputStream = productImage.getInputStream();
			String originalFilename = productImage.getOriginalFilename();
			// 参数应该是相对于编译部署后的项目的web文件夹【根目录】来说,获取绝对路径
			String realPath = request.getServletContext().getRealPath(SystemConstant.IMAGE_UPLOAD_PATH);
			String uploadedFileName = FileUtil.copyFile(inputStream, originalFilename, realPath);
			// 将路径信息保存数据库的表中
			// 上传图片时存到表里面的路径应该是 相对路径【项目根目录】
			product.setProductImagePath(SystemConstant.IMAGE_UPLOAD_PATH+uploadedFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 增加产品
		productService.addProduct(product);
		// ========================上传子图[图片]=======================
		// 将本地硬盘上的文件上传到服务器的硬盘上，这个 网络 上传的过程是框架帮我们完成的
		// 文件的拷贝【将已经上传到服务器硬盘上的临时文件拷贝到指定的目录下】
		// 图片通常应该放在项目的web文件夹【编译部署后的】
		try {
			for (MultipartFile productChildImage : productChildImages) {
				InputStream inputStream = productChildImage.getInputStream();
				String originalFilename = productChildImage.getOriginalFilename();
				// 参数应该是相对于编译部署后的项目的web文件夹【根目录】来说,获取绝对路径
				String realPath = request.getServletContext().getRealPath(SystemConstant.IMAGE_UPLOAD_PATH);
				String uploadedFileName = FileUtil.copyFile(inputStream, originalFilename, realPath);
				// 将路径信息保存数据库的表中
				// 上传图片时存到表里面的路径应该是 相对路径【项目根目录】
				ProductImage productImageInfo = new ProductImage();
				productImageInfo.setImagePath(SystemConstant.IMAGE_UPLOAD_PATH+uploadedFileName);
				productImageInfo.getProduct().setId(product.getId());
				productImageService.addProductImage(productImageInfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 增加完后，要 重定向 到列表页
		return "redirect:/product/list.jhtml";
	}

	public static void main(String[] args) {
		Map stuMap = new HashMap();
		stuMap.put("userName", "zhangsan");
		stuMap.put("userAge", 30);
		stuMap.put("userSex", "男");
		List<String> bookNames = new ArrayList<>();
		bookNames.add("水浒");
		bookNames.add("水浒1");
		bookNames.add("水浒2");
		stuMap.put("bookNames", bookNames);


		Map stuMap1 = new HashMap();
		stuMap1.put("userName", "zhangsan1");
		stuMap1.put("userAge", 31);
		stuMap1.put("userSex", "男1");
		List<String> bookNames1 = new ArrayList<>();
		bookNames1.add("三国");
		bookNames1.add("三国1");
		bookNames1.add("三国2");
		stuMap1.put("bookNames", bookNames1);


		Map stuMap2 = new HashMap();
		stuMap2.put("userName", "zhangsan2");
		stuMap2.put("userAge", 32);
		stuMap2.put("userSex", "男2");
		List<String> bookNames2 = new ArrayList<>();
		bookNames2.add("西游");
		bookNames2.add("西游1");
		bookNames2.add("西游2");
		stuMap2.put("bookNames", bookNames2);

		List<Map> stuList = new ArrayList<>();
		stuList.add(stuMap);
		stuList.add(stuMap1);
		stuList.add(stuMap2);

		for (Map mapInfo : stuList) {
			Iterator iterator = mapInfo.keySet().iterator();
			while (iterator.hasNext()) {
				String key = (String) iterator.next();
				if (key.equals("bookNames")) {
					List<String> bookNameInfos = (List<String>) mapInfo.get(key);
					StringBuffer sb = new StringBuffer();
					for (String bookNameInfo : bookNameInfos) {
						sb.append(bookNameInfo).append(",");
					}
					System.out.println(key + ":" + sb.toString());
				} else {
					System.out.println(key+":"+mapInfo.get(key));
				}
			}
			System.out.println("===============");
		}



//		Iterator iterator = stuMap.entrySet().iterator();
//		while (iterator.hasNext()) {
//			Map.Entry next = (Map.Entry) iterator.next();
//			System.out.println(next.getKey()+":"+next.getValue());
//		}
//
//		Iterator iterator1 = stuMap.keySet().iterator();
//		while (iterator1.hasNext()) {
//			String key = (String) iterator1.next();
//			System.out.println(key+":"+stuMap.get(key));
//		}

	}

	
	@RequestMapping("list")
	@ResponseBody
	public ServerResponse list(Product product, Integer start, Integer length, Integer draw, HttpServletRequest request) {
		// 获取所有请求的参数
		Map<String, String[]> parameterMap = request.getParameterMap();
		// 构建datatatable所需要的数据
		DataTableResult result = productService.buildProductDataTable(product, start, length, draw, parameterMap);
		// 返回结果
		return ServerResponse.success(result);
	}






	@RequestMapping("deleteproduct")
	@ResponseBody
	public ServerResponse delete(Integer id, HttpServletResponse response) {
		productService.delete(id);
		return ServerResponse.success();
	}
	

	@RequestMapping("deleteBatchProduct")
	public void deleteBatchProduct(String ids, HttpServletResponse response) {
		Map result = new HashMap();
		productService.deleteBatchProduct(ids);
		result.put("status", "ok");
		// 将java对象转换为json格式的字符串
		String json = JSONObject.fromObject(result).toString();
		outJson(json, response);
	}
	
	@RequestMapping("findproduct")
	public ModelAndView findproduct(Integer id) {
		ModelAndView modelAndView = new ModelAndView("product/update");
		// 获取产品信息
		Product product = productService.findproduct(id);
		// 获取子图信息
		List<ProductImage> productImageList = productImageService.findProductImageList(id);
		modelAndView.addObject("product", product);
		modelAndView.addObject("productImageList", productImageList);
		return modelAndView;
	}
	
	@RequestMapping("updateProduct")
	@Log("修改产品")
	public String updateProduct(Product product,
								@RequestParam MultipartFile productImage,
								@RequestParam MultipartFile[] productChildImages,
								HttpServletRequest request) throws IOException {
		// 获取项目在硬盘上的根目录
		String rootPath = getRootPath(request);
		// 转换后的文件信息
		FileInfo fileInfo = convertFileInfo(productImage);
		List<FileInfo> fileInfos = convertFileInfoList(productChildImages);
		// 更新数据库中的路径信息
		productService.updateProduct(product, fileInfo, rootPath, fileInfos);
		// 修改完后，要 重定向 到列表页
		return "redirect:/product/list.jhtml"; 
	}




}
