package com.fh.shop.backend.biz;

import com.fh.shop.backend.biz.product.IProductService;
import com.fh.shop.backend.po.product.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-biz.xml"})
public class TestProduct extends AbstractJUnit4SpringContextTests {
    @Resource(name = "productService")
    private IProductService productService;

    @Test
    public void testList() {
        Product product = new Product();
        product.setStartPos(4);
        product.setPageSize(3);
        List<Product> list = productService.findList(product);
        System.out.println(list);
    }
}
