package com.fh.shop.backend.biz.dept;

import com.fh.shop.backend.po.dept.Dept;

import java.util.List;

public interface IDeptService {

    public List<Dept> findList();

    void addDept(Dept dept);

    void updateDept(Dept dept);

    void deleteDept(List<Integer> ids);
}
