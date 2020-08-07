package io.sansam.service.mq.receiver.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * <p>
 * MyDirectReceiver2
 * </p>
 *
 * @author houcb
 * @since 2020-08-06 17:52
 */
@Component
@Slf4j
@RabbitListener(queues = "MyDirectQueue")
public class MyDirectReceiver2 {

    @RabbitHandler
    public String process(Map msg) {

        log.info(this.getClass().getName() + " MyDirectQueue队列获取消息 : " + msg.toString());

        return null;
    }
}
