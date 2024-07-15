package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.ICourseResourceService;
import com.itcourse.mdcc.domain.CourseResource;
import com.itcourse.mdcc.query.CourseResourceQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseResource")
public class CourseResourceController {

    @Autowired
    public ICourseResourceService courseResourceService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody CourseResource courseResource){
        if(courseResource.getId()!=null){
            courseResourceService.updateById(courseResource);
        }else{
            courseResourceService.insert(courseResource);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        courseResourceService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(courseResourceService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(courseResourceService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody CourseResourceQuery query){
        Page<CourseResource> page = new Page<CourseResource>(query.getPage(),query.getRows());
        page = courseResourceService.selectPage(page);
        return JSONResult.success(new PageList<CourseResource>(page.getTotal(),page.getRecords()));
    }
}
