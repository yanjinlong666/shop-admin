package com.fh.shop.backend.web.controller.dept;


import com.fh.core.common.ServerResponse;
import com.fh.shop.backend.biz.dept.IDeptService;
import com.fh.shop.backend.web.controller.BaseController;
import com.fh.shop.backend.po.dept.Dept;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("/dept")
@RestController
public class DeptController extends BaseController {
    @Resource(name="deptService")
    private IDeptService deptService;

    @RequestMapping("/findList")
    public ServerResponse findList() {
        List<Dept> deptList = deptService.findList();
        return ServerResponse.success(deptList);
    }

    @RequestMapping("/addDept")
    public ServerResponse addDept(Dept dept) {
        deptService.addDept(dept);
        return ServerResponse.success(dept.getId());
    }

    @RequestMapping("/updateDept")
    public ServerResponse updateDept(Dept dept) {
        deptService.updateDept(dept);
        return ServerResponse.success();
    }

    @RequestMapping("/deleteDept")
    public ServerResponse deleteDept(@RequestParam("ids[]") List<Integer> ids) {
        deptService.deleteDept(ids);
        return ServerResponse.success();
    }

}
