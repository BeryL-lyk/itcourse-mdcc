package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.IUserAccountService;
import com.itcourse.mdcc.domain.UserAccount;
import com.itcourse.mdcc.query.UserAccountQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAccount")
public class UserAccountController {

    @Autowired
    public IUserAccountService userAccountService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody UserAccount userAccount){
        if(userAccount.getId()!=null){
            userAccountService.updateById(userAccount);
        }else{
            userAccountService.insert(userAccount);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        userAccountService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(userAccountService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(userAccountService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody UserAccountQuery query){
        Page<UserAccount> page = new Page<UserAccount>(query.getPage(),query.getRows());
        page = userAccountService.selectPage(page);
        return JSONResult.success(new PageList<UserAccount>(page.getTotal(),page.getRecords()));
    }
}
