package com.fh.shop.backend.mapper.product;

import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.po.product.ProductImage;

import java.util.List;

public interface IProductImageMapper {

    public void addProductImage(ProductImage productImage);

    List<ProductImage> findProductImageList(Integer id);

    List<ProductImage> findProductImageListById(List<Integer> idList);

    void deleteProductImages(List<Integer> idList);

    void addBatchImage(List<ProductImage> productImageList);
}
