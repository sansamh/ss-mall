package io.sansam.spring.registry;

import io.sansam.spring.bean.BeanDefinition;


/**
 * <p>
 * BeanDefinitionRegistry
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:23
 */
public interface BeanDefinitionRegistry {


    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

}
