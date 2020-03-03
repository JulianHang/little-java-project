package tech.zlia.interest.example;

import java.util.concurrent.CompletableFuture;

/**
 * CompletableFuture的使用示例
 * @author - zlia
 * @version 2020-03-03
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception{
        CompletableFutureTest completableFutureTest = new CompletableFutureTest();
//        completableFutureTest.testThenApply();
//        completableFutureTest.testThenAccept();
//        completableFutureTest.testThenRun();
//        completableFutureTest.testThenCombine();
//        completableFutureTest.testThenAcceptBoth();
//        completableFutureTest.testRunAfterBoth();
//        completableFutureTest.testApplyToEither();
//        completableFutureTest.testAcceptEither();
//        completableFutureTest.testRunAfterEither();
//        completableFutureTest.testThenCompose();
//        completableFutureTest.testWhenComplete();
//        completableFutureTest.testHandle();
//        completableFutureTest.testAllOf();
//        completableFutureTest.testAnyOf();
        completableFutureTest.testCancel();
    }

    /**
     *  异步执行,有执行结果
     */
    @SuppressWarnings("unused")
    private void testSupplyAsync() {
        CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行任务");
            return "1";
        });
    }

    /**
     * 异步执行，无执行结果
     */
    @SuppressWarnings("unused")
    private void testRunAsync() {
        CompletableFuture cf = CompletableFuture.runAsync(() -> System.out.println("执行任务"));
    }

    /**
     * 同步执行，阶段之间具有依赖关系使阶段的执行结果会有所依赖
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testThenApply() throws Exception{

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Hello");

        // 此时的t对应的是cf的执行结果值，即t = Hello，此时completableFuture1的执行结果受cf的执行结果所影响
        CompletableFuture<String> completableFuture1 = cf.thenApply( t -> t + "World");
        System.out.println(completableFuture1.get());

        // 此时的t对应的是completableFuture1的执行结果，即Hello World,此时completableFuture2的执行结果受completableFuture1的执行结果所影响
        CompletableFuture completableFuture2 = completableFuture1.thenApply( t -> t + ", I love China");
        System.out.println(completableFuture2.get());
    }

    /**
     * 同步执行，即使阶段之间具有依赖关系，但每个阶段的执行结果都是null
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testThenAccept() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Hello");

        // 此时的t对应的是cf的执行结果值，即t = Hello，但是completableFuture1的执行结果并不会被影响
        CompletableFuture completableFuture1 = cf.thenAccept( t -> System.out.println(t + "-执行任务"));
        System.out.println(completableFuture1.get());
    }

    /**
     * 同步执行，即使阶段之间具有依赖关系，但每个阶段的执行结果都是null，且不依赖其入参
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testThenRun() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Hello");

        // 此时并无入参，但是要等cf执行完毕后才能执行该阶段
        CompletableFuture completableFuture1 = cf.thenRun(() -> System.out.println("执行任务"));

        System.out.println(completableFuture1.get());
    }

    /**
     * 同步执行，阶段之间具有多重依赖关系，使阶段的执行结果会有多重依赖
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testThenCombine() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("Hello");


        CompletableFuture<String> cf1 = new CompletableFuture<>();
        cf1.complete("World");

        // 此时t1代表cf的执行结果，t2代表cf1的执行结果,此时completableFuture的执行结果受cf、cf1的执行结果所影响
        CompletableFuture completableFuture = cf.thenCombine(cf1, (t1, t2) -> t1 + t2 + ", I love China");
        System.out.println(completableFuture.get());
    }

    /**
     * 同步执行，即使阶段之间具有多重依赖关系，但阶段的执行结果始终是null
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testThenAcceptBoth() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("1");

        CompletableFuture<String> cf1 = new CompletableFuture<>();
        cf1.complete("2");

        // 此时t1代表cf的执行结果，t2代表cf1的执行结果,但是cf2的执行结果始终都是null
        CompletableFuture cf2 = cf.thenAcceptBoth(cf1, (t1, t2) -> System.out.println(t1 + t2 + ", I love China"));
        System.out.println(cf2.get());
    }

    /**
     * 同步执行，即使阶段之间具有多重依赖关系，但每个阶段的执行结果都是null，且不依赖其入参
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testRunAfterBoth() throws Exception{

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("3");

        CompletableFuture<String> cf1 = new CompletableFuture<>();
        cf1.complete("4");

        CompletableFuture cf2 = cf.runAfterBoth(cf1, () -> System.out.println("I love China"));
        System.out.println(cf2.get());
    }

    /**
     * 同步执行，阶段之间具有依赖关系使阶段的执行结果会有所依赖
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testApplyToEither() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("5");

        CompletableFuture<String> cf1 = new CompletableFuture<>();
        cf1.complete("6");

        // 此时t有可能是cf或cf1，就看谁先执行完毕就用谁的
        CompletableFuture cf2 = cf.applyToEither(cf1, (t) -> t + ",I love China");
        System.out.println(cf2.get());
    }

    /**
     * 同步执行，即使阶段之间具有多重依赖关系，但阶段的执行结果始终是null
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testAcceptEither() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("7");

        CompletableFuture<String> cf1 = new CompletableFuture<>();
        cf1.complete("8");

        // 此时t有可能是cf或cf1，就看谁先执行完毕就用谁的
        CompletableFuture cf2 = cf.acceptEither(cf1, (t) -> System.out.println(t + ",I love China"));
        System.out.println(cf2.get());
    }

    /**
     * 同步执行，即使阶段之间具有多重依赖关系，但每个阶段的执行结果都是null，且不依赖其入参
     * 异步执行也是同理
     */
    @SuppressWarnings("unused")
    private void testRunAfterEither() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("9");

        CompletableFuture<String> cf1 = new CompletableFuture<>();
        cf1.complete("10");

        // 此时t有可能是cf或cf1，就看谁先执行完毕就用谁的
        CompletableFuture cf2 = cf.runAfterEither(cf1, () -> System.out.println("I love China"));
        System.out.println(cf2.get());
    }

    /**
     * 组合多个依赖
     */
    @SuppressWarnings("unused")
    private void testThenCompose() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("11");

        // 此时t是cf的执行结果
        CompletableFuture cf2 = cf.thenCompose((t) -> CompletableFuture.supplyAsync(() -> t + "执行thenCompse"));
        System.out.println(cf2.get());
    }

    /**
     * 当阶段执行完成后运行指定动作，其执行结果与调用阶段的执行结果一样
     */
    @SuppressWarnings("unused")
    private void testWhenComplete() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        CompletableFuture cf1 = cf.whenComplete((t, x) -> { // t代表cf正常的执行结果，x代表cf异常的执行结果
            System.out.println("正常执行的信息：" + t);
            System.out.println("异常执行的信息：" + x);
        });

//        cf.complete("执行完成");
        cf.completeExceptionally(new RuntimeException("异常执行"));
        System.out.println(cf1.get()); // cf1的执行结果就是cf的执行结果，若是正常执行的话就是一样的结果，若是异常执行的话get就会抛出异常
    }

    /**
     * 当阶段执行完成后运行指定动作，其执行结果由自身定义
     */
    @SuppressWarnings("unused")
    private void testHandle() throws Exception {

        CompletableFuture<String> cf = new CompletableFuture<>();
        CompletableFuture cf1 = cf.handle((t, x) -> { // t代表cf正常的执行结果，x代表cf异常的执行结果
            System.out.println("正常执行的信息：" + t);
            System.out.println("异常执行的信息：" + x);
            return "我的结果由我自己做主";
        });

        cf.complete("执行完成");
//        cf.completeExceptionally(new RuntimeException("异常执行"));
        System.out.println(cf1.get()); // cf1的执行结果由自己做主
    }

    /**
     * 等待指定多个阶段全部执行完毕后才能执行当前阶段，其执行结果始终都为null
     */
    @SuppressWarnings("unused")
    private void testAllOf() throws Exception {

        CompletableFuture<String> a = new CompletableFuture<>();
        a.complete("12");

        CompletableFuture<String> b = new CompletableFuture<>();
        b.complete("13");

        CompletableFuture<String> c = new CompletableFuture<>();
        c.complete("14");

        CompletableFuture<String> d = new CompletableFuture<>();
        d.complete("15");

        CompletableFuture<String> e = new CompletableFuture<>();
        e.complete("16");

        System.out.println(a.get());
        System.out.println(b.get());
        System.out.println(c.get());
        System.out.println(d.get());
        System.out.println(e.get());

        // 必须等待abcde执行完毕了才能执行，其执行结果始终为null
        CompletableFuture cf = CompletableFuture.allOf(a, b, c, d, e);
        System.out.println(cf.get());
    }

    /**
     * 等待指定多个阶段中任何一个执行完毕后就可以执行当前阶段，其执行结果与任何一个阶段执行完成的执行结果一样
     */
    @SuppressWarnings("unused")
    private void testAnyOf() throws Exception {

        CompletableFuture<String> a = new CompletableFuture<>();
//        a.complete("17");

        CompletableFuture<String> b = new CompletableFuture<>();
        b.complete("18");

        CompletableFuture<String> c = new CompletableFuture<>();
//        c.complete("19");

        CompletableFuture<String> d = new CompletableFuture<>();
//        d.complete("20");

        CompletableFuture<String> e = new CompletableFuture<>();
//        e.complete("21");


        // 等待指定多个阶段中任何一个执行完毕后才能执行，其执行结果与任何一个阶段执行完成的执行结果一样
        CompletableFuture cf = CompletableFuture.anyOf(a, b, c, d, e);
        System.out.println(cf.get());
    }

    /**
     * 取消当前调用阶段
     */
    @SuppressWarnings("unused")
    private void testCancel() {

        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("我已经执行完毕了");

        boolean result = cf.cancel(true);
        System.out.println(result);
    }
}
