package tech.zlia.interest.annotation;

import java.lang.annotation.Annotation;
import java.text.MessageFormat;

/**
 * 测试自定义注解
 * @version  1.0 2019-05-12
 * @author zlia
 */
public class TestFeature {

    public static void main(String[] args) {
        Class<TestAnnotation> clazz = TestAnnotation.class;
        Annotation[] annotaions = clazz.getAnnotations();

        for (Annotation annotaion : annotaions) {
            if (annotaion instanceof  CustomizeAnnotation) {
                int age = ((CustomizeAnnotation) annotaion).age();
                String name = ((CustomizeAnnotation) annotaion).name();
                System.out.println(MessageFormat.format("注解中的age字段：{0}", age));
                System.out.println(MessageFormat.format("注解中的name字段：{0}", name));
            }
        }
    }
}

/* 结果展示
 * 注解中的age字段：20
 * 注解中的name字段：lisi
 */
