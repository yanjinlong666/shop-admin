package com.fh.shop.backend.biz.product;

import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.po.product.ProductImage;

import java.util.List;

public interface IProductImageService {

    public void addProductImage(ProductImage productImage);

    public List<ProductImage> findProductImageList(Integer id);
}
