package io.sansam.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * LinuxCondition
 * </p>
 *
 * @author houcb
 * @since 2020-07-21 15:46
 */
public class LinuxCondition implements Condition {

    private static final String LINUX_MATCH_ENV = "linux";

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        List<AnnotationAttributes> allAnnotationAttributes =
                annotationAttributesFromMultiValueMap(
                        annotatedTypeMetadata.getAllAnnotationAttributes(ConditionOnLinux.class.getName()));
        String env;
        for (AnnotationAttributes attribute : allAnnotationAttributes) {
            env = attribute.getString("env");
            if (LINUX_MATCH_ENV.equals(env)) {
                return true;
            }
        }

        return false;
    }

    private List<AnnotationAttributes> annotationAttributesFromMultiValueMap(
            MultiValueMap<String, Object> multiValueMap) {
        List<Map<String, Object>> maps = new ArrayList<>();
        multiValueMap.forEach((key, value) -> {
            for (int i = 0; i < value.size(); i++) {
                Map<String, Object> map;
                if (i < maps.size()) {
                    map = maps.get(i);
                } else {
                    map = new HashMap<>();
                    maps.add(map);
                }
                map.put(key, value.get(i));
            }
        });
        List<AnnotationAttributes> annotationAttributes = new ArrayList<>(maps.size());
        for (Map<String, Object> map : maps) {
            annotationAttributes.add(AnnotationAttributes.fromMap(map));
        }
        return annotationAttributes;
    }
}
