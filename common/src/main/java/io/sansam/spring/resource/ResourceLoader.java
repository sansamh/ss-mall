package io.sansam.spring.resource;

/**
 * <p>
 * ResourceLoader
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:10
 */
public class ResourceLoader {

    final String CLASS_PATH_PREFIX = "classpath:";

    /**
     * 获取classpath资源
     *
     * @param path
     * @return
     */
    public Resource getResource(String path) {
        if (path.startsWith(CLASS_PATH_PREFIX)) {
            return new ClassPathResource(path.substring(CLASS_PATH_PREFIX.length()), null);
        } else {
            return new ClassPathResource(path, null);
        }
    }

}
