package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.ICourseViewLogService;
import com.itcourse.mdcc.domain.CourseViewLog;
import com.itcourse.mdcc.query.CourseViewLogQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseViewLog")
public class CourseViewLogController {

    @Autowired
    public ICourseViewLogService courseViewLogService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody CourseViewLog courseViewLog){
        if(courseViewLog.getId()!=null){
            courseViewLogService.updateById(courseViewLog);
        }else{
            courseViewLogService.insert(courseViewLog);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        courseViewLogService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(courseViewLogService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(courseViewLogService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody CourseViewLogQuery query){
        Page<CourseViewLog> page = new Page<CourseViewLog>(query.getPage(),query.getRows());
        page = courseViewLogService.selectPage(page);
        return JSONResult.success(new PageList<CourseViewLog>(page.getTotal(),page.getRecords()));
    }
}
