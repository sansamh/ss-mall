package io.sansam.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * <p>
 * RabbitTemplateConfig
 * </p>
 *
 * @author houcb
 * @since 2020-08-07 16:57
 */
@Configuration
public class RabbitTemplateConfig {

    @Resource
    RabbitMQConfirmCallback rabbitMQConfirmCallback;

    @Bean
    public RabbitTemplate getRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(connectionFactory);
        template.setMandatory(true);
        template.setConfirmCallback(rabbitMQConfirmCallback);
        template.setReturnCallback(rabbitMQConfirmCallback);
        return template;
    }
}
