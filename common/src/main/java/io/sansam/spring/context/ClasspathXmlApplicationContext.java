package io.sansam.spring.context;

import io.sansam.spring.factory.DefaultListableBeanFactory;
import io.sansam.spring.reader.XmlBeanDefinitionReader;
import io.sansam.spring.resource.ResourceLoader;

/**
 * <p>
 * ClasspathXmlApplicationContext
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 14:39
 */
public class ClasspathXmlApplicationContext extends AbstractApplicationContext {

    private String location;

    public ClasspathXmlApplicationContext(String location) {
        this.location = location;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinition(location);
    }
}
