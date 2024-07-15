package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.ICourseSummaryService;
import com.itcourse.mdcc.domain.CourseSummary;
import com.itcourse.mdcc.query.CourseSummaryQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseSummary")
public class CourseSummaryController {

    @Autowired
    public ICourseSummaryService courseSummaryService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody CourseSummary courseSummary){
        if(courseSummary.getId()!=null){
            courseSummaryService.updateById(courseSummary);
        }else{
            courseSummaryService.insert(courseSummary);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        courseSummaryService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(courseSummaryService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(courseSummaryService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody CourseSummaryQuery query){
        Page<CourseSummary> page = new Page<CourseSummary>(query.getPage(),query.getRows());
        page = courseSummaryService.selectPage(page);
        return JSONResult.success(new PageList<CourseSummary>(page.getTotal(),page.getRecords()));
    }
}
