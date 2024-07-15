package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.ITeacherService;
import com.itcourse.mdcc.domain.Teacher;
import com.itcourse.mdcc.query.TeacherQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    public ITeacherService teacherService;

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody Teacher teacher){
        if(teacher.getId()!=null){
            teacherService.updateById(teacher);
        }else{
            teacherService.insert(teacher);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        teacherService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(teacherService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(teacherService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody TeacherQuery query){
        Page<Teacher> page = new Page<Teacher>(query.getPage(),query.getRows());
        page = teacherService.selectPage(page);
        return JSONResult.success(new PageList<Teacher>(page.getTotal(),page.getRecords()));
    }
}
