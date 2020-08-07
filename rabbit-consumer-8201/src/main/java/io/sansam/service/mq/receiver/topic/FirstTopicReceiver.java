package io.sansam.service.mq.receiver.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 * FirstTopicReceiver
 * </p>
 *
 * @author houcb
 * @since 2020-08-07 10:21
 */
@Component
@Slf4j
@RabbitListener(queues = "topic.first")
public class FirstTopicReceiver {

    @RabbitHandler
    public String process(Object msg) {
        log.info(this.getClass().getName() + " topic.first 队列获取消息 : " + msg.toString());
        return null;
    }
}
