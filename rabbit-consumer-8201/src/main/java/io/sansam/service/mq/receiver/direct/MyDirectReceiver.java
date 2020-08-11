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
 * MyDirectReceiver
 * </p>
 *
 * @author houcb
 * @since 2020-08-06 17:39
 */
@Component
@Slf4j
public class MyDirectReceiver {

    @RabbitListener(queues = "MyDirectQueue")
    @RabbitHandler
    @RabbitMQAck
    public String process(Message message, Channel channel) throws Exception {
        String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("消费者RabbitDemoConsumer从RabbitMQ服务端消费消息：" + msg);
        if ("bad".equals(msg)) {
            throw new IllegalArgumentException("测试：抛出可重回队列的异常");
        }
        if ("error".equals(msg)) {
            throw new Exception("测试：抛出无需重回队列的异常");
        }


        return null;
    }
}
