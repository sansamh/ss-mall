package io.sansam.spring.bean;

import lombok.Data;

/**
 * <p>
 * BeanDefinition
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:15
 */
@Data
public class BeanDefinition {

    private String beanName;

    private Class beanClass;

    private String beanClassName;

    /**
     * 属性值
     */
    private PropertyValues propertyValues;


}
