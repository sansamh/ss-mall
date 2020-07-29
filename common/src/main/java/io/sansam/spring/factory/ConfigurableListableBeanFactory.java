package io.sansam.spring.factory;

/**
 * <p>
 * ConfigurableListableBeanFactory
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 14:58
 */
public interface ConfigurableListableBeanFactory extends BeanFactory {

    /**
     * 初始化所有单例bean
     *
     * @throws Exception
     */
    void preInstantiateSingletons() throws Exception;
}
