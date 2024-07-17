package com.itcourse.mdcc.web.controller;

import com.itcourse.mdcc.service.IMediaFileService;
import com.itcourse.mdcc.domain.MediaFile;
import com.itcourse.mdcc.query.MediaFileQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/mediaFile")
public class MediaFileController {

    @Autowired
    public IMediaFileService mediaFileService;

    //文件注册，检查文件是否已经上传
    @PostMapping("/register")
    public JSONResult register(@RequestParam("fileMd5") String fileMd5,     // 文件唯一标识
                               @RequestParam("fileName") String fileName,   // 文件名
                               @RequestParam("fileSize") Long fileSize,     // 文件大小
                               @RequestParam("mimetype") String mimetype,   // mime类型
                               @RequestParam("fileExt") String fileExt) {   //文件扩展名

        log.info("文件上传-文件注册,fileName={},fileMd5={}",fileName,fileMd5);

        return mediaFileService.register(fileMd5,fileName,fileSize,mimetype,fileExt);
    }

    /**
    * 保存和修改公用的
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody MediaFile mediaFile){
        if(mediaFile.getId()!=null){
            mediaFileService.updateById(mediaFile);
        }else{
            mediaFileService.insert(mediaFile);
        }
        return JSONResult.success();
    }

    /**
    * 删除对象
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id){
        mediaFileService.deleteById(id);
        return JSONResult.success();
    }

    /**
   * 获取对象
   */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id")Long id){
        return JSONResult.success(mediaFileService.selectById(id));
    }


    /**
    * 查询所有对象
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public JSONResult list(){
        return JSONResult.success(mediaFileService.selectList(null));
    }


    /**
    * 带条件分页查询数据
    */
    @RequestMapping(value = "/pagelist",method = RequestMethod.POST)
    public JSONResult page(@RequestBody MediaFileQuery query){
        Page<MediaFile> page = new Page<MediaFile>(query.getPage(),query.getRows());
        page = mediaFileService.selectPage(page);
        return JSONResult.success(new PageList<MediaFile>(page.getTotal(),page.getRecords()));
    }
}
