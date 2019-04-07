/** 
 * <pre>项目名称:shop-admin 
 * 文件名称:IProductMapper.java 
 * 包名:com.fh.shop.backend.mapper.product 
 * 创建日期:2018年12月24日上午11:33:44 
 * Copyright (c) 2018, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.fh.shop.backend.mapper.product;

import java.util.List;

import com.fh.shop.backend.po.product.Product;
import com.fh.shop.backend.po.product.Product;

/** 
 * <pre>项目名称：shop-admin    
 * 类名称：IProductMapper    
 * 类描述：    
 * 创建人：于笑扬 yuxy123@gmail.com    
 * 创建时间：2018年12月24日 上午11:33:44    
 * 修改人：于笑扬 yuxy123@gmail.com     
 * 修改时间：2018年12月24日 上午11:33:44    
 * 修改备注：       
 * @version </pre>    
 */
public interface IProductMapper {
	
	public void addProduct(Product product);

	/** <pre>findList(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月25日 上午10:13:23    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月25日 上午10:13:23    
	 * 修改备注： 
	 * @return</pre>    
	 */
	public List<Product> findList(Product product);

	/** <pre>delete(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月25日 下午12:50:18    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月25日 下午12:50:18    
	 * 修改备注： 
	 * @param id</pre>    
	 */
	public void deleteProduct(Integer id);

	/** <pre>deleteBatchProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月25日 下午8:34:37    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月25日 下午8:34:37    
	 * 修改备注： 
	 * @param idList</pre>    
	 */
	public void deleteBatchProduct(List<Integer> idList);

	/** <pre>findproduct(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月26日 上午10:17:04    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月26日 上午10:17:04    
	 * 修改备注： 
	 * @param id
	 * @return</pre>    
	 */
	public Product findproduct(Integer id);

	/** <pre>updateProduct(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月26日 上午10:47:46    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月26日 上午10:47:46    
	 * 修改备注： 
	 * @param product</pre>    
	 */
	public void updateProduct(Product product);

	/** <pre>findProductListCount(这里用一句话描述这个方法的作用)   
	 * 创建人：于笑扬 yuxy123@gmail.com    
	 * 创建时间：2018年12月26日 下午12:16:41    
	 * 修改人：于笑扬 yuxy123@gmail.com     
	 * 修改时间：2018年12月26日 下午12:16:41    
	 * 修改备注： 
	 * @param product
	 * @return</pre>    
	 */
	public Long findProductListCount(Product product);

    List<Product> findProductList(Product product);

    List<Product> findAllProductList();
}
