package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.ICourseService;
import com.itcourse.mdcc.domain.Course;
import com.itcourse.mdcc.query.CourseQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    public ICourseService courseService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody Course course){
        if(course.getId()!=null){
            courseService.updateById(course);
        }else{
            courseService.insert(course);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        courseService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(courseService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(courseService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody CourseQuery query){
        Page<Course> page = new Page<Course>(query.getPage(),query.getRows());
        page = courseService.selectPage(page);
        return JSONResult.success(new PageList<Course>(page.getTotal(),page.getRecords()));
    }
}
