package tech.zlia.interest.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解
 * <p>了解注解的工作方式
 * @version 1.0 2019-05-12
 * @author zlia
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Repeatable(AnnotationContainer.class)
public @interface CustomizeAnnotation {

    String name() default "zhangsan";
    int age() default 10;
}


/* @Documented - 注解是否将包含在javadoc中
 * @Retention - 定义该注解的生命周期
 *            - RetentionPolicy.SOURCE - 在编译阶段丢弃，这些注解在编译结束之后就不再有任何意义，所以它们不会写入字节码
 *            - RetentionPolicy.CLASS - 在类加载的时候丢弃，在字节码文件的处理中有用，注解默认使用这种方式
 *            - RetentionPolicy.RUNTIME - 始终不会丢弃，运行期也保留该注解，因此可以使用反射机制读取该注解的信息，自定义注解通常使用该方式
 * @Target - 表示该注解用于什么地方，如果不明确指出，该注解可以放在任何地方
 *            - ElementType.TYPE - 用于描述类、接口或枚举
 *            - ElementType.FIELD - 用于描述实例变量
 *            - ElementType.PARAMETER
 *            - ElementType.CONSTRUCTOR
 *            - ElementType.LOCAL_VARIABLE
 *            - ElementType.ANNOTATION_TYPE - 另一个注释
 *            - ElementType.PACKAGE - 用于记录java文件的package信息
 * @Inherited - 定义该注解和子类的关系，允许子类继承父类中的注解
 * @Repeatable - 可重复定义该注解，只不过通过getAnnotation来获取时只会拿到容器对象，即AnnotationContainer，具体写法参考测试类
 */
