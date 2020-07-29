package io.sansam.spring.factory;

import io.sansam.spring.bean.BeanDefinition;
import io.sansam.spring.bean.BeanReference;
import io.sansam.spring.bean.PropertyValue;
import io.sansam.spring.registry.BeanDefinitionRegistry;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * DefaultListableBeanFactory
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:36
 */
public class DefaultListableBeanFactory extends AbstractBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(16);

    private List<String> beanNameList = new ArrayList<>(16);

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        if (beanDefinitionMap.containsKey(beanName)) {
            throw new IllegalArgumentException("beanName " + beanName + " is duplicated");
        }
        beanDefinitionMap.put(beanName, beanDefinition);
        beanNameList.add(beanName);
    }

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        Object bean = createInstance(beanDefinition);
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) {
        List<PropertyValue> list = beanDefinition.getPropertyValues().getPropertyValueList();
        if (list != null && !list.isEmpty()) {
            for (PropertyValue propertyValue : list) {
                String name = propertyValue.getName();
                Object val = propertyValue.getValue();
                if (val instanceof BeanReference) {
                    val = getBean(name);
                }
                try {
                    PropertyDescriptor descriptor = new PropertyDescriptor(name, bean.getClass());
                    descriptor.getWriteMethod().invoke(bean, val);

                } catch (Exception e) {
                    throw new RuntimeException("inject bean property " + name + " failed");

                }
            }
        }

    }

    private Object createInstance(BeanDefinition beanDefinition) {
        try {
            if (beanDefinition.getBeanClass() != null) {
                return beanDefinition.getBeanClass().newInstance();
            }
            if (beanDefinition.getBeanClassName() != null) {
                return Class.forName(beanDefinition.getBeanClassName()).newInstance();
            }
            throw new RuntimeException("bean name for " + beanDefinition.getBeanName() + " not define bean class and " +
                    "bean class name");

        } catch (Exception e) {
            throw new RuntimeException("create bean " + beanDefinition.getBeanName() + " failed");
        }


    }

    @Override
    protected BeanDefinition getBeanDefenitionByBeanName(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public void preInstantiateSingletons() {
        for (String beanName : beanNameList) {
            getBean(beanName);
        }
    }
}
