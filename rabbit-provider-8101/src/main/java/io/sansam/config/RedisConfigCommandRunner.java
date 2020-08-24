package io.sansam.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Scanner;

/**
 * <p>
 * RedisConfigCommandRunner
 * </p>
 *
 * @author houcb
 * @since 2020-08-24 17:05
 */
@Component
@Slf4j
public class RedisConfigCommandRunner implements CommandLineRunner {

    @Autowired
    RedisConfig redisConfig;

    @Autowired
    ReloadConfig reloadConfig;

    @Override
    public void run(String... args) throws Exception {
        String input;
        Scanner sc = new Scanner(System.in);
        do {
            reloadConfig.refreshRedisConfig(redisConfig);
            log.info("redisConfig por={}", redisConfig.getPort());
            log.info("------------输入q退出,输入其他值继续运行------------");
            input = StringUtils.trimAllWhitespace(sc.nextLine());
        } while (!input.equalsIgnoreCase("q"));
        log.info("----------系统退出成功----------");
        sc.close();
    }
}
