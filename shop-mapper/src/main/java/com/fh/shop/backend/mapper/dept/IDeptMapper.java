package com.fh.shop.backend.mapper.dept;

import com.fh.shop.backend.po.dept.Dept;

import java.util.List;

public interface IDeptMapper {

    public List<Dept> findList();

    void addDept(Dept dept);

    void updateDept(Dept dept);

    void deleteDept(List<Integer> ids);
}
