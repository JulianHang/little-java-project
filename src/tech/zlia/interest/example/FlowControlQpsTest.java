package tech.zlia.interest.example;

import java.util.concurrent.TimeUnit;

/**
 * 模拟QPS-实现流量控制效果
 * @version - 1.0.0 2019-04-07
 * @author - zlia
 * @since - 1.8
 */
public class FlowControlQpsTest {

    /**
     * 请求数上限
     */
    private final int limit;

    /**
     * 控制请求数的时间间隔
     */
    private final long duration;

    /**
     * 总共的请求数
     */
    private int total;

    /**
     * 指定时间间隔内的请求数
     */
    private int count;

    /**
     * 请求的开始时间
     */
    private long start;

    /**
     * 第一次请求的开始时间
     */
    private long start0 = System.nanoTime();

    /**
     * 自定义时间间隔
     * @param limit 请求数上限
     * @param duration 控制请求数的时间间隔
     */
    FlowControlQpsTest(int limit, long duration){
        this.limit = limit;
        this.duration = duration;
    }

    /**
     * 使用默认的时间间隔
     * @param qps 请求数上限
     */
    FlowControlQpsTest(int qps){
        this(qps,1000);
    }

    /**
     * 检查请求数是否在指定的时间间隔内已经达到上限
     * 计算指定时间间隔内的请求数与总共的请求数
     * @return 请求数是否在指定的时间间隔内达到上限
     */
    public boolean checkQps(){
        if (count < limit ) {
            count++;
            total++;
            return true;
        }

        if (System.nanoTime() - start > duration) {
            total++;
            reset();
            return true;
        }

        return false;
    }

    /**
     * 重置请求数与请求的开始时间
     * 每次过完一个时间间隔后都要重置
     */
    public void reset(){
        count = 0;
        start = System.nanoTime();
    }

    /**
     * 获取总共的请求数
     * @return 总共的请求数
     */
    public int total(){
        return total;
    }

    /**
     * 计算从第一次请求开始的时间到当前时间的请求数
     * @return 请求数
     */
    public int qps(){
        return (int) (TimeUnit.SECONDS.toNanos(total)/(System.nanoTime() - start0));
    }
}
