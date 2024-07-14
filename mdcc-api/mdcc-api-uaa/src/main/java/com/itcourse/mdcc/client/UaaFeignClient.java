package com.itcourse.mdcc.client;

import com.itcourse.mdcc.domain.Login;
import com.itcourse.mdcc.result.JSONResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("service-uaa")
@RequestMapping("/login")
public interface UaaFeignClient {
    @PostMapping("/save")
    JSONResult saveOrUpdate(@RequestBody Login login);
}
