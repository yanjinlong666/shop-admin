package com.fh.shop.backend.biz.product;

import com.fh.shop.backend.mapper.product.IProductImageMapper;
import com.fh.shop.backend.po.product.ProductImage;
import com.fh.shop.backend.po.product.ProductImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productImageService")
public class IProductImageServiceImpl implements IProductImageService {
    @Autowired
    private IProductImageMapper productImageMapper;

    @Override
    public void addProductImage(ProductImage productImage) {
        productImageMapper.addProductImage(productImage);
    }

    @Override
    public List<ProductImage> findProductImageList(Integer id) {
        List<ProductImage> productImageList = productImageMapper.findProductImageList(id);
        return productImageList;
    }
}
