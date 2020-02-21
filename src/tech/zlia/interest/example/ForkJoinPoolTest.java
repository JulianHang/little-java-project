package tech.zlia.interest.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ForkJoinPool的使用示例
 * @author  zlia
 * @version - 2020-02-21
 */
public class ForkJoinPoolTest {

    public static void main(String[] args) throws Exception{

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        CountTaskForkJoinTest countTaskForkJoinTest = new CountTaskForkJoinTest(0, 100); // 大任务
        forkJoinPool.submit(countTaskForkJoinTest); // 提交任务

        Long result = countTaskForkJoinTest.get(); // 获取结果
        System.out.println(result);
    }
}

class CountTaskForkJoinTest extends RecursiveTask<Long> {

    private static final long serialVersionUID = 1L;

    private static final int threshold = 100;//临界值
    private long start;
    private long end;

    public CountTaskForkJoinTest(long start, long end) {
        this.start = start;
        this.end = end;
    }

    /**
     * 重写compute方法，判断是否将任务进行拆分计算
     */
    @Override
    protected Long compute() {
        long sum = 0;
        //判断是否是拆分完毕
        boolean canCompute = (end - start) <= threshold;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 将任务拆分成子任务
            long middle = (start + end) / 2;
            CountTaskForkJoinTest task1 = new CountTaskForkJoinTest(start, middle);
            CountTaskForkJoinTest task2 = new CountTaskForkJoinTest(middle, end);

            task1.fork();
            task2.fork();

            sum = task1.join() + task2.join();
        }
        return sum;
    }
}
