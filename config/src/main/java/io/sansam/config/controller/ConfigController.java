package io.sansam.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Administrator
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${config.data}")
    private String configData;

    @GetMapping("/configData")
    public String getConfigData() {
        return configData;
    }
}
