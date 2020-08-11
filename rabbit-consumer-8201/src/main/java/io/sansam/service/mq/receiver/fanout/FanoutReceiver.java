package io.sansam.service.mq.receiver.fanout;

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
 * FanoutReceiver
 * </p>
 *
 * @author houcb
 * @since 2020-08-10 10:26
 */
@Component
@Slf4j
public class FanoutReceiver {

    @RabbitListener(queues = "fanout.A")
    @RabbitHandler
    @RabbitMQAck
    public String processFanoutA(Message message, Channel channel) throws Exception {

        final String msg = new String(message.getBody(), StandardCharsets.UTF_8);
        log.info("消费者RabbitDemoConsumer从RabbitMQ服务端消费消息：" + msg);
//        if (msg.contains("bad")) {
//            throw new IllegalArgumentException("测试：抛出可重回队列的异常");
//        }
//        if (msg.contains("error")) {
//            throw new Exception("测试：抛出无需重回队列的异常");
//        }


        return null;
    }

    @RabbitListener(queues = "fanout.B")
    @RabbitHandler
    @RabbitMQAck
    public String processFanoutB(Message message, Channel channel) throws Exception {
        final String msg = new String(message.getBody(), StandardCharsets.UTF_8);

//        log.info("消费者RabbitDemoConsumer从RabbitMQ服务端消费消息：" + msg);
//        if (msg.contains("bad")) {
//            throw new IllegalArgumentException("测试：抛出可重回队列的异常");
//        }
//        if (msg.contains("error")) {
//            throw new Exception("测试：抛出无需重回队列的异常");
//        }


        return null;
    }

    @RabbitListener(queues = "fanout.C")
    @RabbitHandler
    @RabbitMQAck
    public String processFanoutC(Message message, Channel channel) throws Exception {
        final String msg = new String(message.getBody());

//        log.info("消费者RabbitDemoConsumer从RabbitMQ服务端消费消息：" + msg);
//        if (msg.contains("bad")) {
//            throw new IllegalArgumentException("测试：抛出可重回队列的异常");
//        }
//        if (msg.contains("error")) {
//            throw new Exception("测试：抛出无需重回队列的异常");
//        }


        return null;
    }
}
