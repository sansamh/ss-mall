package io.sansam.spring.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>
 * PropertyValue
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:19
 */
@Data
@AllArgsConstructor
public class PropertyValue {

    private String name;

    private Object value;
}
