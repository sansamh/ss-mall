package io.sansam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * TopicExchangeConfig
 * </p>
 *
 * @author houcb
 * @since 2020-08-07 10:10
 */
@Configuration
public class TopicExchangeConfig {

    public static final String FIRST = "topic.first";
    public static final String SECOND = "topic.second";

    @Bean
    public Queue firstTopicQueue() {
        return new Queue(FIRST);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(SECOND);
    }

    @Bean
    public TopicExchange myTopicExchange() {
        return new TopicExchange("MyTopicExchange");
    }

    @Bean
    Binding bindingFirst() {
        return BindingBuilder.bind(firstTopicQueue()).to(myTopicExchange()).with(FIRST);
    }

    @Bean
    Binding bindingSecond() {
        return BindingBuilder.bind(secondQueue()).to(myTopicExchange()).with(SECOND);
    }


}
