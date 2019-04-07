package com.fh.shop.backend.biz;

import com.fh.shop.backend.biz.brand.IBrandService;
import com.fh.shop.backend.po.brand.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-biz.xml"})
public class TestBrandBiz extends AbstractJUnit4SpringContextTests {
    @Resource(name = "brandService")
    private IBrandService brandService;

    @Test
    public void testList() {
        List<Brand> brandList = brandService.findBrandList();
        System.out.println(brandList);
    }
}
