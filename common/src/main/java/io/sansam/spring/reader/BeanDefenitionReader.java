package io.sansam.spring.reader;

/**
 * <p>
 * BeanDefenitionReader
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:21
 */
public interface BeanDefenitionReader {

    void loadBeanDefinition(String location) throws Exception;
}
