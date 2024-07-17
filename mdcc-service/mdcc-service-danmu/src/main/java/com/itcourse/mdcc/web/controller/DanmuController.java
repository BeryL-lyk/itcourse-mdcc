package com.itcourse.mdcc.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.itcourse.mdcc.service.IDanmuService;
import com.itcourse.mdcc.domain.Danmu;
import com.itcourse.mdcc.query.DanmuQuery;
import com.itcourse.mdcc.result.JSONResult;
import com.itcourse.mdcc.result.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("弹幕服务")
@RestController
@RequestMapping("/danmu")
public class DanmuController {

    @Autowired
    public IDanmuService danmuService;

    @ApiOperation("保存弹幕")
    @PostMapping("/server/v3/")
    public JSONObject save(@RequestBody Danmu danmu) {

        // 前端传过来的id是弹幕池id 也就是媒体id 需要处理一下
        danmu.setMediaId(danmu.getId());

        danmu.setId(null);

        danmuService.insert(danmu);

        // 封装返回数据
        JSONArray dataArray = new JSONArray();
        JSONArray item = new JSONArray();
        item.add(danmu.getTime());
        item.add(danmu.getType());
        item.add(danmu.getColor());
        item.add(danmu.getAuthor());
        item.add(danmu.getText());
        dataArray.add(item);

        // 构造 JSON 数据
        JSONObject result = new JSONObject();
        result.put("code", 0);
        // 将 dataArray 添加到 result
        result.put("data", dataArray);

        return result;
    }

    @ApiOperation("加载弹幕")
    @GetMapping("/server/v3/")
    public JSONObject load(@RequestParam("id") Long mediaId) {
        //TODO 可以放在redis中

        // 查询当前视频下的弹幕
        Wrapper<Danmu> wrapper = new EntityWrapper<Danmu>();
        wrapper.eq("media_id", mediaId);
        List<Danmu> danmus = danmuService.selectList(wrapper);

        JSONArray dataArray = new JSONArray();

        // 转换成前端接收的格式
        for (Danmu danmu : danmus) {
            JSONArray item = new JSONArray();
            item.add(danmu.getTime());
            item.add(danmu.getType());
            item.add(danmu.getColor());
            item.add(danmu.getAuthor());
            item.add(danmu.getText());
            dataArray.add(item);
        }

        // 构造 JSON 数据
        JSONObject result = new JSONObject();
        result.put("code", 0);
        // 将 dataArray 添加到 result
        result.put("data", dataArray);

        return result;
    }

    /**
     * 保存和修改公用的
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JSONResult saveOrUpdate(@RequestBody Danmu danmu) {
        if (danmu.getId() != null) {
            danmuService.updateById(danmu);
        } else {
            danmuService.insert(danmu);
        }
        return JSONResult.success();
    }

    /**
     * 删除对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JSONResult delete(@PathVariable("id") Long id) {
        danmuService.deleteById(id);
        return JSONResult.success();
    }

    /**
     * 获取对象
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JSONResult get(@PathVariable("id") Long id) {
        return JSONResult.success(danmuService.selectById(id));
    }


    /**
     * 查询所有对象
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JSONResult list() {
        return JSONResult.success(danmuService.selectList(null));
    }


    /**
     * 带条件分页查询数据
     */
    @RequestMapping(value = "/pagelist", method = RequestMethod.POST)
    public JSONResult page(@RequestBody DanmuQuery query) {
        Page<Danmu> page = new Page<Danmu>(query.getPage(), query.getRows());
        page = danmuService.selectPage(page);
        return JSONResult.success(new PageList<Danmu>(page.getTotal(), page.getRecords()));
    }
}
