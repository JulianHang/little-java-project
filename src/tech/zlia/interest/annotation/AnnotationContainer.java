package tech.zlia.interest.annotation;

import java.lang.annotation.*;

/**
 * 存放重复注解的数据
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface AnnotationContainer {
    CustomizeAnnotation[] value();
}
