package tech.zlia.interest.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 * 此种代理实现必须要实现InvocationHandler
 * @version 2019-1-24
 * @author zlia
 * @see java.lang.reflect.InvocationHandler
 */
public class DynamicProxy implements InvocationHandler {

    private Subject real;

    /**
     * 构造函数，传入到代理的真实对象
     * @param real
     */
    public DynamicProxy(Subject real){
        this.real = real;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(real,args);
    }
}
