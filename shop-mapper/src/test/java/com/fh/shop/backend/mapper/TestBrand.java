package com.fh.shop.backend.mapper;

import com.fh.shop.backend.mapper.brand.IBrandMapper;
import com.fh.shop.backend.po.brand.Brand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class TestBrand extends AbstractJUnit4SpringContextTests {

    @Autowired
    private IBrandMapper brandMapper;

    @Test
    public void testAdd() {
        Brand brand = new Brand();
        brand.setBrandName("小黑");
        brandMapper.addBrand(brand);
    }

}
