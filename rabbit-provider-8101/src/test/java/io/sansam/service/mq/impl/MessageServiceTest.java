package io.sansam.service.mq.impl;

import io.sansam.service.mq.IMessageService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageServiceTest {

    @Resource
    IMessageService messageService;

    @Test
    void send() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, String> map = new HashMap<>();
        map.put("msgId", UUID.randomUUID().toString());
        map.put("msgContent", "hello rabbit, " + now);
        map.put("createTime", now);

        String exchangeName = "MyDirectExchange";
        String routingKey = "MyDirectRoutingKey";

        String send = messageService.sendTopic(routingKey, exchangeName, map);

        Assert.assertNotNull(send);

    }
}