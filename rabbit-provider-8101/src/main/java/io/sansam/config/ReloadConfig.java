package io.sansam.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.springframework.stereotype.Component;

/**
 * <p>
 * ReloadConfig
 * </p>
 *
 * @author houcb
 * @since 2020-08-24 16:57
 */
@Component
@Slf4j
public class ReloadConfig {

    private PropertiesConfiguration configuration;

    public ReloadConfig() {
        init();
    }

    public void init() {
        log.info("ReloadConfig init() method invoke");
        try {
            configuration = new PropertiesConfiguration("redis.properties");
            FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
            strategy.setRefreshDelay(5000L);// 刷新周期5s
            configuration.setReloadingStrategy(strategy);
        } catch (Exception e) {
            log.error("init failed", e);
        }

    }

    public void refreshRedisConfig(RedisConfig redisConfig) {
        redisConfig.setPort(configuration.getInt("redis.port"));
    }

}
