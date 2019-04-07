/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:ProductServiceImpl.java 
 * 包名:com.fh.shop.backend.biz.product 
 * 创建日期:2018年12月23日下午4:19:24 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;

import com.fh.core.common.DataTableResult;
import com.fh.core.common.FileInfo;
import com.fh.core.util.DateUtil;
import com.fh.core.util.FileUtil;
import com.fh.shop.backend.biz.BaseService;
import com.fh.shop.backend.mapper.product.IProductImageMapper;
import com.fh.shop.backend.mapper.product.IProductMapper;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.util.SystemConstant;
import com.fh.shop.backend.vo.ProductVo;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：ProductServiceImpl    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月23日 下午4:19:24    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月23日 下午4:19:24    
 * 修改备注：       
 * @version </pre>    
 */
// <bean id="productService" class="com.fh.shop.backend.biz.product.ProductServiceImpl"></bean>
@Service("productService")
public class ProductServiceImpl extends BaseService implements IProductService {
	@Autowired
	private IProductMapper productMapper;
	@Autowired
	private IProductImageMapper productImageMapper;
	@Resource(name="productImageService")
	private IProductImageService productImageService;

    @Override
    public DataTableResult buildProductDataTable(Product product, Integer start, Integer length, Integer draw, Map<String, String[]> parameterMap) {
		// 构建排序信息
		buildOrder(parameterMap, product);
		// 设置分页信息
		product.setStartPos(start);
		product.setPageSize(length);
		// 获取总条数
		Long totalCount = findProductListCount(product);
		// 获取分页列表
		List<Product> productList = findList(product);
		// PO转VO
		List<ProductVo> productVoList = wrapperProductVo(productList);
		// 组装结果
		return DataTableResult.build(draw, totalCount, totalCount, productVoList);
//		return new DataTableResult.Builder().data(productList).draw(draw).recordsFiltered(totalCount).recordsTotal(totalCount)
//				.build();

    }

	private List<ProductVo> wrapperProductVo(List<Product> productList) {
		List<ProductVo> productVoList = new ArrayList<>();
		for (Product productInfo : productList) {
			ProductVo productVo = new ProductVo();
			productVo.setId(productInfo.getId());
			productVo.setBrandName(productInfo.getBrand().getBrandName());
			productVo.setInsertTimeStr(DateUtil.date2Str(productInfo.getInsertTime(), DateUtil.FULL_DATE));
			productVo.setUpdateTimeStr(DateUtil.date2Str(productInfo.getUpdateTime(), DateUtil.FULL_DATE));
			productVo.setProductImagePath(productInfo.getProductImagePath());
			productVo.setProductName(productInfo.getProductName());
			productVo.setProductPrice(productInfo.getProductPrice());
			productVoList.add(productVo);
		}
		return productVoList;
	}

	private void buildOrder(Map<String, String[]> parameterMap, Product product) {
		Gson gson = new Gson();
		// json格式的字符串转换为指定的java对象
		Map fieldMapping = gson.fromJson(SystemConstant.FIELD_MAPPING, Map.class);
		// 提取排序的字段
		String orderField = "";
		if (parameterMap.get(SystemConstant.ORDER_COLUMN) != null) {
			String orderColumn = parameterMap.get(SystemConstant.ORDER_COLUMN)[0];
			String orderFieldTemp = parameterMap.get(getCloumnData(orderColumn))[0];
			String orderInfo = (String) fieldMapping.get(orderFieldTemp);
			orderField = StringUtils.isEmpty(orderInfo) ? orderFieldTemp : orderInfo;
		}
		// 提取排序的方向
		String orderDirection = "";
		if (parameterMap.get(SystemConstant.ORDER_DIR) != null) {
			orderDirection = parameterMap.get(SystemConstant.ORDER_DIR)[0];
		}
		product.setSortField(orderField);
		product.setSort(orderDirection);
	}




    /* (non-Javadoc)
	 * @see com.fh.shop.backend.biz.product.IProductService#addProduct(com.fh.shop.backend.po.product.Product)    
	 */
	@Override
	public void addProduct(Product product){
		Date now = new Date();
		product.setInsertTime(now);
		product.setUpdateTime(now);
		productMapper.addProduct(product);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#findList()    
	 */
	@Override
	public List<Product> findList(Product product) {
		List<Product> productList = productMapper.findList(product);
		return productList;
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#delete(java.lang.Integer)    
	 */
	@Override
	public void delete(Integer id) {
		productMapper.deleteProduct(id);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#deleteBatchProduct(java.lang.String)    
	 */
	@Override
	public void deleteBatchProduct(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			List<Integer> idList = new ArrayList<>();
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				idList.add(Integer.parseInt(id));
			}
			productMapper.deleteBatchProduct(idList);
		}
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#findproduct(java.lang.Integer)    
	 */
	@Override
	public Product findproduct(Integer id) {
		Product product = productMapper.findproduct(id);
		return product;
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#updateProduct(com.fh.shop.backend.po.product.Product)    
	 */
	@Override
	public void updateProduct(Product product, FileInfo fileInfo, String rootPath, List<FileInfo> fileInfos) {
		// 更新产品主图
		updateProductMainImage(product, fileInfo, rootPath);
		// 更新子图
		updateChildImage(product, rootPath, fileInfos);
		// 重新设置更新时间
		product.setUpdateTime(new Date());
		// 更新产品
		productMapper.updateProduct(product);
	}

	private void updateChildImage(Product product, String rootPath, List<FileInfo> fileInfos) {
		deleteOldChildImage(product, rootPath);
		addChildImage(product, rootPath, fileInfos);
	}

	private void addChildImage(Product product, String rootPath, List<FileInfo> fileInfos) {
		List<ProductImage> productImageList = new ArrayList<>();
		// 上传新子图
		for (FileInfo info : fileInfos) {
			InputStream inputStream = info.getIs();
			String originalFilename = info.getFileName();
			// 参数应该是相对于编译部署后的项目的web文件夹【根目录】来说,获取绝对路径
			String realPath = rootPath + SystemConstant.IMAGE_UPLOAD_PATH;
			String uploadedFileName = FileUtil.copyFile(inputStream, originalFilename, realPath);
			// 将路径信息保存数据库的表中
			// 上传图片时存到表里面的路径应该是 相对路径【项目根目录】
			ProductImage productImageInfo = new ProductImage();
			productImageInfo.setImagePath(SystemConstant.IMAGE_UPLOAD_PATH+uploadedFileName);
			productImageInfo.getProduct().setId(product.getId());
			productImageList.add(productImageInfo);
		}
		// 批量插入子图
		if (productImageList.size() > 0) {
			productImageMapper.addBatchImage(productImageList);
		}
	}

	private void deleteOldChildImage(Product product, String rootPath) {
		String ids = product.getIds();
		if (ids.length() > 0) {
			// 删除旧的子图
			// 查询出id对应子图信息select...in
			String[] idArr = ids.substring(1).split(",");
			List<Integer> idList = new ArrayList<>();
			for (String s : idArr) {
				idList.add(Integer.parseInt(s));
			}
			List<ProductImage> productImageList = productImageMapper.findProductImageListById(idList);
			for (ProductImage productImage : productImageList) {
				FileUtil.deleteFile(rootPath + productImage.getImagePath());
			}
			// 删除数据库表中的记录
			productImageMapper.deleteProductImages(idList);
		}
	}

	private void updateProductMainImage(Product product, FileInfo fileInfo, String rootPath) {
		if (fileInfo.getSize() > 0) {
			// 删除旧图片
			deleteOldImage(product, rootPath);
			// 增加新图片
			addImage(product, fileInfo, rootPath);
		}
	}

	private void addImage(Product product, FileInfo fileInfo, String rootPath) {
		String ImageRealPath = rootPath+SystemConstant.IMAGE_UPLOAD_PATH;
		// 重新拷贝上传的新图片
		String uploadedFileName = FileUtil.copyFile(fileInfo.getIs(), fileInfo.getFileName(), ImageRealPath);
		// 更新表里面的路径信息
		product.setProductImagePath(SystemConstant.IMAGE_UPLOAD_PATH+uploadedFileName);
	}

	private void deleteOldImage(Product product, String rootPath) {
		String productImagePath = product.getProductImagePath();
		String realPath = rootPath+productImagePath;
		FileUtil.deleteFile(realPath);
	}

	/* (non-Javadoc)    
	 * @see com.fh.shop.backend.biz.product.IProductService#findProductListCount(com.fh.shop.backend.po.product.Product)    
	 */
	@Override
	public Long findProductListCount(Product product) {
		Long count = productMapper.findProductListCount(product);
		return count;
	}

    @Override
    public List<Product> findProductList(Product product) {
		List<Product> productList = productMapper.findProductList(product);
		return productList;
    }

    @Override
    public List<ProductVo> findAllProductList() {
		List<Product> productList = productMapper.findAllProductList();
		List<ProductVo> productVoList = new ArrayList<>();
		for (Product product : productList) {
			ProductVo productVo = new ProductVo();
			productVo.setId(product.getId());
			productVo.setProductPrice(product.getProductPrice());
			productVo.setProductName(product.getProductName());
			productVoList.add(productVo);
		}
        return productVoList;
    }

}
