package com.itcourse.mdcc.client;

import com.itcourse.mdcc.client.fallback.MediaFeignClientFactory;
import com.itcourse.mdcc.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "service-media",fallbackFactory = MediaFeignClientFactory.class)
@RequestMapping("/mediaFile")
public interface MediaFeignClient {

    //根据课程id获取视频列表
    @GetMapping("/getByCourseId/{courseId}")
    JSONResult getByCourseId(@PathVariable("courseId") Long courseId);


}
