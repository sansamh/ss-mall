package io.sansam.spring.factory;

import io.sansam.spring.bean.BeanDefinition;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * AbstractBeanFactory
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:40
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    // 缓存 单例bean map
    private ConcurrentHashMap<String, Object> singletonObjectMap = new ConcurrentHashMap<>(16);

    @Override
    public Object getBean(String beanName) {
        if (singletonObjectMap.containsKey(beanName)) {
            return singletonObjectMap.get(beanName);
        }

        BeanDefinition beanDefinition = getBeanDefenitionByBeanName(beanName);
        if (beanDefinition == null) {
            throw new RuntimeException(beanName + " not defined in application");
        }

        Object bean = doCreateBean(beanDefinition);
        this.singletonObjectMap.put(beanName, bean);

        return bean;
    }

    protected abstract Object doCreateBean(BeanDefinition beanDefinition);

    protected abstract BeanDefinition getBeanDefenitionByBeanName(String beanName);

}
