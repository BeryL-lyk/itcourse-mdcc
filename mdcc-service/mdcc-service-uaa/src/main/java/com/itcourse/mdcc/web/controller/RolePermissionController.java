package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.IRolePermissionService;
import com.itcourse.mdcc.domain.RolePermission;
import com.itcourse.mdcc.query.RolePermissionQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    public IRolePermissionService rolePermissionService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody RolePermission rolePermission){
        if(rolePermission.getId()!=null){
            rolePermissionService.updateById(rolePermission);
        }else{
            rolePermissionService.insert(rolePermission);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        rolePermissionService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(rolePermissionService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(rolePermissionService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody RolePermissionQuery query){
        Page<RolePermission> page = new Page<RolePermission>(query.getPage(),query.getRows());
        page = rolePermissionService.selectPage(page);
        return JSONResult.success(new PageList<RolePermission>(page.getTotal(),page.getRecords()));
    }
}
