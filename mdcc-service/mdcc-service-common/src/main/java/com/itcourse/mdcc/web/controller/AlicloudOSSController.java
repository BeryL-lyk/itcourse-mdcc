package com.itcourse.mdcc.web.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.itcourse.mdcc.properties.AlicloudOSSProperties;
import com.itcourse.mdcc.result.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/file")
public class AlicloudOSSController {

    @Autowired
    private AlicloudOSSProperties properties;


    @GetMapping("/oss/sign")
    public JSONResult ossSign(){
        // host的格式为 bucketname.endpoint
        String host = "https://" + properties.getBucketName() + "." + properties.getEndpoint();
        // 用户上传文件时指定的前缀。
        String dir = "itcourse-mdcc";
        // 创建OSSClient实例。
        OSS ossClient = null;
        try {
            //策略过期时间
            long expireTime = 100;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);

            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder()
                .build(properties.getEndpoint(), properties.getAccessKey(), properties.getSecretKey());

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            //签名
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            //返回签名及OSS相关参数
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", properties.getAccessKey());
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));

            return JSONResult.success(respMap);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            if(ossClient != null){
                ossClient.shutdown();
            }
        }
        return null;
    }
}