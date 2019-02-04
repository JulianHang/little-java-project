package tech.zlia.interest.proxy.jdk;

import tech.zlia.interest.reflection.ReflectionClass;

import java.lang.reflect.Proxy;

/**
 * 测试类
 * @version 2019-1-24
 * @author zlia
 */
public class TestFeature {

    public static void main(String[] args) {
        Subject real = new RealSubject();
        DynamicProxy dynamicProxy = new DynamicProxy(real);
        Subject proxy = (Subject)Proxy.newProxyInstance(dynamicProxy.getClass().getClassLoader(),real.getClass().getInterfaces(),dynamicProxy);
        proxy.hello();
    }
}

/* 结果展示
hello world
 */
