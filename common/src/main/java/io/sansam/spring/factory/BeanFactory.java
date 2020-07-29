package io.sansam.spring.factory;

/**
 * <p>
 * BeanFactory
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:40
 */
public interface BeanFactory {

    Object getBean(String beanName);
}
