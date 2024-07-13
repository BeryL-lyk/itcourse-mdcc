package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.IUserAddressService;
import com.itcourse.mdcc.domain.UserAddress;
import com.itcourse.mdcc.query.UserAddressQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    public IUserAddressService userAddressService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody UserAddress userAddress){
        if(userAddress.getId()!=null){
            userAddressService.updateById(userAddress);
        }else{
            userAddressService.insert(userAddress);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        userAddressService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(userAddressService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(userAddressService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody UserAddressQuery query){
        Page<UserAddress> page = new Page<UserAddress>(query.getPage(),query.getRows());
        page = userAddressService.selectPage(page);
        return JSONResult.success(new PageList<UserAddress>(page.getTotal(),page.getRecords()));
    }
}
