/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:IProductService.java 
 * 包名:com.fh.shop.backend.biz.product 
 * 创建日期:2018年12月23日下午4:18:28 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.biz.product;


import com.fh.core.common.DataTableResult;
import com.fh.core.common.FileInfo;
import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.vo.ProductVo;

import java.util.List;
import java.util.Map;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：IProductService    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月23日 下午4:18:28    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月23日 下午4:18:28    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductService {

	public DataTableResult buildProductDataTable(Product product, Integer start, Integer length, Integer draw, Map<String, String[]> parameterMap);
	
	public void addProduct(Product product);

	/** <pre>findList(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月25日 上午10:09:32    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月25日 上午10:09:32    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Product> findList(Product product);

	/** <pre>delete(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月25日 下午12:49:56    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月25日 下午12:49:56    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void delete(Integer id);

	/** <pre>deleteBatchProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月25日 下午8:24:15    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月25日 下午8:24:15    
	 * 修改备注： 
	 * @param ids</pre>    
	 */
	public void deleteBatchProduct(String ids);

	/** <pre>findproduct(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月26日 上午10:16:00    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月26日 上午10:16:00    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Product findproduct(Integer id);

	/** <pre>updateProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月26日 上午10:46:57    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月26日 上午10:46:57    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void updateProduct(Product product, FileInfo fileInfo, String rootPath, List<FileInfo> fileInfos);

	/** <pre>findProductListCount(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月26日 下午12:15:41    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月26日 下午12:15:41    
	 * 修改备注： 
	 * @param product
	 * @return</pre>    
	 */
	public Long findProductListCount(Product product);

	List<Product> findProductList(Product product);

    List<ProductVo> findAllProductList();

}
