package io.sansam.spring.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * PropertyValues
 * </p>
 *
 * @author houcb
 * @since 2020-07-10 11:18
 */
public class PropertyValues {

    private List<PropertyValue> propertyValueList = new ArrayList<>(16);

    public void addPropertyValue(PropertyValue p) {
        propertyValueList.add(p);
    }

    public List<PropertyValue> getPropertyValueList() {
        return propertyValueList;
    }
}
