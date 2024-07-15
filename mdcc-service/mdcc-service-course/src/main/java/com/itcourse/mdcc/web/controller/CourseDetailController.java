package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.ICourseDetailService;
import com.itcourse.mdcc.domain.CourseDetail;
import com.itcourse.mdcc.query.CourseDetailQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseDetail")
public class CourseDetailController {

    @Autowired
    public ICourseDetailService courseDetailService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody CourseDetail courseDetail){
        if(courseDetail.getId()!=null){
            courseDetailService.updateById(courseDetail);
        }else{
            courseDetailService.insert(courseDetail);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        courseDetailService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(courseDetailService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(courseDetailService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody CourseDetailQuery query){
        Page<CourseDetail> page = new Page<CourseDetail>(query.getPage(),query.getRows());
        page = courseDetailService.selectPage(page);
        return JSONResult.success(new PageList<CourseDetail>(page.getTotal(),page.getRecords()));
    }
}
