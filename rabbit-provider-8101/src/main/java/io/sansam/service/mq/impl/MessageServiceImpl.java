package io.sansam.service.mq.impl;

import io.sansam.service.mq.IMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * MessageServiceImpl
 * </p>
 *
 * @author houcb
 * @since 2020-08-06 14:52
 */
@Service
public class MessageServiceImpl implements IMessageService {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public String sendDirect(String routingKey, String exchangeName, Object msg) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        return "send direct ok";
    }

    @Override
    public String sendTopic(String routingKey, String exchangeName, Object msg) {
        rabbitTemplate.convertAndSend(exchangeName, routingKey, msg);
        return "send topic ok";
    }
}
