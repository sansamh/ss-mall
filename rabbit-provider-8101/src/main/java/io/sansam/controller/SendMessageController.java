package io.sansam.controller;

import io.sansam.service.mq.IMessageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * SendMessageController
 * </p>
 *
 * @author houcb
 * @since 2020-08-06 17:54
 */
@RestController
public class SendMessageController {

    @Resource
    IMessageService messageService;


    @GetMapping(value = "/send/direct")
    public String sendDirect() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, String> map = new HashMap<>();
        map.put("msgId", UUID.randomUUID().toString());
        map.put("msgContent", "hello rabbit, " + now);
        map.put("createTime", now);

        String exchangeName = "MyDirectExchange";
        String routingKey = "MyDirectRoutingKey";

        return messageService.sendDirect(routingKey, exchangeName, map);
    }

    @GetMapping(value = "/send/direct/msg")
    public String sendDirect(@RequestParam("msg") String msg) {

        String exchangeName = "MyDirectExchange";
        String routingKey = "MyDirectRoutingKey123";

        return messageService.sendDirect(routingKey, exchangeName, msg);
    }

    @GetMapping(value = "/send/topic/first")
    public String sendTopicWithFirst() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, String> map = new HashMap<>();
        map.put("msgId", UUID.randomUUID().toString());
        map.put("msgContent", "First Queue Message");
        map.put("createTime", now);

        String exchangeName = "MyTopicExchange";
        String routingKey = "topic.first";

        return messageService.sendTopic(routingKey, exchangeName, map);
    }

    @GetMapping(value = "/send/topic/second")
    public String sendTopicWithSecond() {
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, String> map = new HashMap<>();
        map.put("msgId", UUID.randomUUID().toString());
        map.put("msgContent", "Second Queue Message");
        map.put("createTime", now);

        String exchangeName = "MyTopicExchange";
        String routingKey = "topic.second";

        return messageService.sendTopic(routingKey, exchangeName, map);
    }

}
