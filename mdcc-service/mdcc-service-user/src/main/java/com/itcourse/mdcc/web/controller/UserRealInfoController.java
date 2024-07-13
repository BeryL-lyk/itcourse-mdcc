package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.IUserRealInfoService;
import com.itcourse.mdcc.domain.UserRealInfo;
import com.itcourse.mdcc.query.UserRealInfoQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userRealInfo")
public class UserRealInfoController {

    @Autowired
    public IUserRealInfoService userRealInfoService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody UserRealInfo userRealInfo){
        if(userRealInfo.getId()!=null){
            userRealInfoService.updateById(userRealInfo);
        }else{
            userRealInfoService.insert(userRealInfo);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        userRealInfoService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(userRealInfoService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(userRealInfoService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody UserRealInfoQuery query){
        Page<UserRealInfo> page = new Page<UserRealInfo>(query.getPage(),query.getRows());
        page = userRealInfoService.selectPage(page);
        return JSONResult.success(new PageList<UserRealInfo>(page.getTotal(),page.getRecords()));
    }
}
