package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.ICourseCollectService;
import com.itcourse.mdcc.domain.CourseCollect;
import com.itcourse.mdcc.query.CourseCollectQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseCollect")
public class CourseCollectController {

    @Autowired
    public ICourseCollectService courseCollectService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody CourseCollect courseCollect){
        if(courseCollect.getId()!=null){
            courseCollectService.updateById(courseCollect);
        }else{
            courseCollectService.insert(courseCollect);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        courseCollectService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(courseCollectService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(courseCollectService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody CourseCollectQuery query){
        Page<CourseCollect> page = new Page<CourseCollect>(query.getPage(),query.getRows());
        page = courseCollectService.selectPage(page);
        return JSONResult.success(new PageList<CourseCollect>(page.getTotal(),page.getRecords()));
    }
}
