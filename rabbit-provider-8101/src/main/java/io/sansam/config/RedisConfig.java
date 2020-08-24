package io.sansam.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * <p>
 * RedisConfig
 * </p>
 *
 * @author houcb
 * @since 2020-08-24 16:54
 */
@Component
@PropertySource(value = "classpath:redis.properties")
@Data
public class RedisConfig {

    @Value("${redis.port}")
    private int port;

}
