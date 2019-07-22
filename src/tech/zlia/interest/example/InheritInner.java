package tech.zlia.interest.example;

/**
 * 此例子主要用来说明内部类的继承
 * <p>由于内部类的构造器存在指向外围类对象的引用，所以在继承内部类的时候，引用必须被初始化
 * <p>而具体的语法如下
 * @version   2019-07-23
 * @author  zlia
 */
public class InheritInner extends WithInner.Inner{

    InheritInner(WithInner withInner) {
        withInner.super();
    }
    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}

class WithInner{

    WithInner() {
        System.out.println("WithInner");
    }
    class Inner {}
}