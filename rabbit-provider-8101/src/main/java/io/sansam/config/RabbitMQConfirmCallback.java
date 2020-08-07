package io.sansam.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

/**
 * <p>
 * RabbitMQConfirmCallback
 * </p>
 *
 * @author houcb
 * @since 2020-08-07 16:43
 */
@Component
@Slf4j
public class RabbitMQConfirmCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {

        log.info("ConfirmCallback:     " + "相关数据：" + correlationData);
        log.info("ConfirmCallback:     " + "确认情况：" + ack);
        log.info("ConfirmCallback:     " + "原因：" + cause);

        log.info("------------> end <------------");

    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("ReturnCallback:     " + "消息：" + message);
        log.info("ReturnCallback:     " + "回应码：" + replyCode);
        log.info("ReturnCallback:     " + "回应信息：" + replyText);
        log.info("ReturnCallback:     " + "交换机：" + exchange);
        log.info("ReturnCallback:     " + "路由键：" + routingKey);

    }

    @SuppressWarnings("unchecked")
    private <T> T byteToObject(byte[] bytes, Class<T> clazz) {
        T t;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            t = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }

}
