package tech.zlia.interest.algorithm.tree.heap;


import java.util.Arrays;
import java.util.List;

/**
 * 测试二叉堆
 * @author - zlia
 * @version - 2020-01-10
 */
public class TestFeature {

    public static void main(String[] args) {

        List<Integer> testData = Arrays.asList(5, 9, 4, 3, 2, 1);
        MaximumBinaryHeap<Integer> maximumBinaryHeap = new MaximumBinaryHeap<>(testData);
        System.out.println("插入节点：" + maximumBinaryHeap.getData());

        maximumBinaryHeap.remove(5);
        System.out.println("移除节点：" + maximumBinaryHeap.getData());
    }
}
/* 结果展示

插入节点：[9, 5, 4, 3, 2, 1]
移除节点：[9, 3, 4, 1, 2]

 */