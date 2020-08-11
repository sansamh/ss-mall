package io.sansam.service.mq.receiver.direct;

import com.rabbitmq.client.Channel;
import io.sansam.config.RabbitMQAck;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

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
public class MyDirectReceiver2 {

    @RabbitListener(queues = "MyDirectQueue")
    @RabbitHandler
    @RabbitMQAck
    public String process(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);

        log.info(this.getClass().getName() + " MyDirectQueue队列获取消息 : " + msg);

        return null;
    }
}
