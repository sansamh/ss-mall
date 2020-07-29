package io.sansam.spring.context;

import io.sansam.spring.factory.DefaultListableBeanFactory;

/**
 * <p>
 * AbstractApplicationContext
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 14:39
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    public Object getBean(String beanName) {
        return beanFactory.getBean(beanName);
    }

    public void refresh() throws Exception {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        loadBeanDefinitions(factory);
        this.beanFactory = factory;
        onRefresh();
    }

    private void onRefresh() throws Exception {
        beanFactory.preInstantiateSingletons();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws Exception;
}
