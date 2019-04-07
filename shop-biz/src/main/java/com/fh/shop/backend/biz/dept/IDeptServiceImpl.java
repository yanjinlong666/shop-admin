package com.fh.shop.backend.biz.dept;

import com.fh.core.util.CacheManager;
import com.fh.shop.backend.mapper.dept.IDeptMapper;
import com.fh.shop.backend.po.dept.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("deptService")
public class IDeptServiceImpl implements IDeptService {

    @Autowired
    private IDeptMapper deptMapper;

    @Override
    public List<Dept> findList() {
        CacheManager instance = CacheManager.getInstance();
        Object deptListInfo = instance.getObj("deptList");
        if (deptListInfo != null) {
            return (List<Dept>) deptListInfo;
        }
        List<Dept> deptList = deptMapper.findList();
        instance.putObj("deptList", deptList);
        return deptList;
    }

    @Override
    public void addDept(Dept dept) {
        deptMapper.addDept(dept);
        CacheManager.getInstance().remove("deptList");
    }

    @Override
    public void updateDept(Dept dept) {
        deptMapper.updateDept(dept);
        CacheManager.getInstance().remove("deptList");
    }

    @Override
    public void deleteDept(List<Integer> ids) {
        deptMapper.deleteDept(ids);
        CacheManager.getInstance().remove("deptList");
    }
}
