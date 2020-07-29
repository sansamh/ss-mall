package io.sansam.spring.reader;

import io.sansam.spring.bean.BeanDefinition;
import io.sansam.spring.bean.BeanReference;
import io.sansam.spring.bean.PropertyValue;
import io.sansam.spring.registry.BeanDefinitionRegistry;
import io.sansam.spring.resource.ResourceLoader;
import lombok.Data;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * <p>
 * XmlBeanDefinitionReader
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:22
 */
@Data
public class XmlBeanDefinitionReader implements BeanDefenitionReader {

    private BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void loadBeanDefinition(String location) throws Exception {
        InputStream inputStream = resourceLoader.getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(inputStream);
        registeBeanDefinitions(document);
        inputStream.close();

    }

    private void registeBeanDefinitions(Document root) {
        NodeList nodes = root.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                processBeanDefinition((Element) node);
            }
        }
    }

    private void processBeanDefinition(Element node) {
        String beanName = node.getAttribute("id");
        String className = node.getAttribute("class");
        if (className == null || className.length() == 0) {
            throw new IllegalArgumentException("Configuration exception: <bean> element must has class attribute.");
        }
        if (beanName == null || beanName.length() == 0) {
            beanName = className;
        }
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanName(beanName);
        beanDefinition.setBeanClassName(className);
        // 处理标签中的property标签 作为propertyValue
        processBeanProperty(beanDefinition, node);
        // 注册beanDefinition
        registry.registerBeanDefinition(beanName, beanDefinition);
    }

    private void processBeanProperty(BeanDefinition beanDefinition, Element ele) {
        NodeList childs = ele.getElementsByTagName("property");
        for (int i = 0; i < childs.getLength(); i++) {
            Node node = childs.item(i);
            if (node instanceof Element) {
                Element property = (Element) node;
                String name = property.getAttribute("name");
                String value = property.getAttribute("value");
                // 如果name 和 value都有 则组装一个PropertyValue
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }
                // value为ref一个别的对象
                else {
                    String ref = property.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for " +
                                name + " must specify a value or ref.");
                    }
                    BeanReference reference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, reference));
                }
            }
        }
    }
}
