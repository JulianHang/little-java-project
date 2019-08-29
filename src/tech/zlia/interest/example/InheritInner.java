package tech.zlia.interest.example;

/**
 * 此例子主要用来说明内部类的继承
 * <p>由于内部类的构造器存在指向外围类对象的引用，所以在继承内部类的时候，引用必须被初始化
 * <p>而具体的语法如下
 * <p>实际上应该是super(withInner)，而不是withInner.super()，反正提示中并没有该语法，这可能是Java的固有语法
 * <p>虽然语法上是如此写，但仔细一看字节码文件后，你会发现它最终还是编译成super(withInner)
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