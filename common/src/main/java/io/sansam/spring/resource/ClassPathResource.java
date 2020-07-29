package io.sansam.spring.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * ClassPathResource
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:09
 */
public class ClassPathResource implements Resource {

    private String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path, ClassLoader classLoader) {
        this.path = path;
        this.classLoader = classLoader != null ? classLoader : Thread.currentThread().getContextClassLoader();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return classLoader.getResourceAsStream(path);
    }
}
