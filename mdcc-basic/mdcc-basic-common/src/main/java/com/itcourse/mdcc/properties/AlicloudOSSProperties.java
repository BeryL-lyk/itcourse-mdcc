package com.itcourse.mdcc.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "file.alicloud")
public class AlicloudOSSProperties {

    private String bucketName;
    private String accessKey;
    private String secretKey;
    private String endpoint;
}
