package io.sansam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * <p>
 * RabbitProvider8101Main
 * </p>
 *
 * @author houcb
 * @since 2020-08-06 14:41
 */
@SpringBootApplication
@RestController
public class RabbitProvider8101Main {

    public static void main(String[] args) {
        SpringApplication.run(RabbitProvider8101Main.class, args);
    }


    @GetMapping("/")
    public String index() {
        return LocalDateTime.now().toString();
    }
}

