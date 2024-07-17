package com.itcourse.mdcc.mq;

import com.alibaba.fastjson.JSON;
import com.itcourse.mdcc.domain.MediaFile;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

// 生产者
@Component
@Slf4j
public class MediaProducer {

    @Autowired
    private RocketMQTemplate template;

    public static final String mediaTopic = "media-topic";

    public boolean synSend(MediaFile mediaFile) {

        try {
            String mediaFileJSON = JSON.toJSONString(mediaFile);

            SendResult result = template.syncSend(mediaTopic, MessageBuilder.withPayload(mediaFileJSON).build());

            log.info("媒体文件发送到MQ处理 mediaFile = {}", mediaFile);

            return result.getSendStatus() == SendStatus.SEND_OK;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
