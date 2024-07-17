package com.itcourse.mdcc.client.fallback;

import com.itcourse.mdcc.client.MediaFeignClient;
import com.itcourse.mdcc.result.JSONResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MediaFeignClientFactory implements FallbackFactory<MediaFeignClient> {
    @Override
    public MediaFeignClient create(Throwable throwable) {
        return new MediaFeignClient(){
            @Override
            public JSONResult getByCourseId(Long id) {
                throwable.printStackTrace();
                return JSONResult.error("课程获取失败");
            }

        };
    }
}