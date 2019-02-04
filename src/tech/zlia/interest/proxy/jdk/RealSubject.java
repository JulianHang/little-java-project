package tech.zlia.interest.proxy.jdk;

/**
 * 真实对象
 * <p>实际上代理的是该对象
 * @version 2019-1-24
 * @author zlia
 */
public class RealSubject implements Subject{

    /**
     * 重写父类的方法
     */
    @Override
    public void hello() {
        System.out.println("hello world");
    }
}
