package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.IMessageEmailService;
import com.itcourse.mdcc.domain.MessageEmail;
import com.itcourse.mdcc.query.MessageEmailQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messageEmail")
public class MessageEmailController {

    @Autowired
    public IMessageEmailService messageEmailService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody MessageEmail messageEmail){
        if(messageEmail.getId()!=null){
            messageEmailService.updateById(messageEmail);
        }else{
            messageEmailService.insert(messageEmail);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        messageEmailService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(messageEmailService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(messageEmailService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody MessageEmailQuery query){
        Page<MessageEmail> page = new Page<MessageEmail>(query.getPage(),query.getRows());
        page = messageEmailService.selectPage(page);
        return JSONResult.success(new PageList<MessageEmail>(page.getTotal(),page.getRecords()));
    }
}
