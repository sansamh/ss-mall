package io.sansam.condition;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(LinuxCondition.class)
public @interface ConditionOnLinux {

    String env() default "";
}
