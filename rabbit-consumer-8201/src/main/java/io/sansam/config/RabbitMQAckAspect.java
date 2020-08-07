package io.sansam.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * <p>
 * RabbitMQAckAspect
 * </p>
 *
 * @author houcb
 * @since 2020-08-07 17:26
 */
@Component
@Aspect
@Slf4j
public class RabbitMQAckAspect {

    /**
     * 这里我们使用注解的形式 当然，我们也可以通过切点表达式直接指定需要拦截的package,需要拦截的class 以及 method 切点表达式:
     * execution(...)
     */
    @Pointcut("@annotation(io.sansam.config.RabbitMQAck)")
    public void rabbitMQAckPointCut() {

    }

    /**
     * 环绕通知 @Around ， 当然也可以使用 @Before (前置通知) @After (后置通知)
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("rabbitMQAckPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Message message = (Message) point.getArgs()[1];
        Channel channel = (Channel) point.getArgs()[2];
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        Object result = null;
        try {
            result = point.proceed();
            channel.basicAck(deliveryTag, true);
            log.info("RabbitMQAckAspect MessageId={}, ack!", deliveryTag);
        } catch (IllegalArgumentException e) {
            channel.basicReject(deliveryTag, true);
            log.error("RabbitMQAckAspect ", e);
            log.info("RabbitMQAckAspect MessageId={}, reject 重新入队!", deliveryTag);

        } catch (Exception e) {
            channel.basicReject(deliveryTag, false);
            log.error("RabbitMQAckAspect ", e.getMessage());
            log.info("RabbitMQAckAspect MessageId={}, reject mq抛弃数据!", deliveryTag);

        }
        long time = System.currentTimeMillis() - beginTime;
        log.info("RabbitMQAckAspect 处理耗时 {}", time);

        return result;
    }


}
