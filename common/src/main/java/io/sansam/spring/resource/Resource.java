package io.sansam.spring.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * Resource
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:08
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
