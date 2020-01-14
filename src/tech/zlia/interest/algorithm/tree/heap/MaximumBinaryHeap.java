package tech.zlia.interest.algorithm.tree.heap;

import java.util.ArrayList;
import java.util.List;

/**
 *  二叉堆是一颗完全二叉树
 *  最大堆：父节点的值大于等于子节点
 * @author - zlia
 * @version - 2020-01-09
 */
public class MaximumBinaryHeap<T extends Comparable<T>> {

    /**
     * 用于存储所有节点的数组
     * 考虑到数组可能需要扩容故而直接使用了List
     */
    private List<T> data;

    /**
     * 初始化
     */
    public MaximumBinaryHeap() {
        data = new ArrayList<>();
    }

    /**
     * 初始化
     * @param capacity 数组的容量大小
     */
    public MaximumBinaryHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    /**
     * 初始化
     * @param elements 要添加的节点
     */
    public MaximumBinaryHeap(List<T> elements) {
        data = new ArrayList<>(elements.size());

        for (T element : elements) {
            insert(element);
        }
    }

    /**
     * 从最大堆中插入节点
     * @param element 插入的节点
     */
    public void insert(T element) {
        if (element != null) {
            data.add(element);
            siftUp(data.indexOf(element), element);
        }
    }

    /**
     * 上浮节点以满足二叉堆的性质
     * 1. 插入的节点将放到数组的末尾
     * 2. 然后将插入的节点与父节点进行比较，对于最大堆来说，若当前节点的值大于父节点的值，则两个节点进行值交换，否则直接结束
     * 3. 若插入的节点换到了父节点的位置，查看是否还有父节点，若有则重复进行步骤2，直至不存在父节点或结束
     *
     * 假设一个节点的索引为：N，其父节点的索引为：(N - 1)/2，左子节点的索引为：2N + 1，其右子节点的索引为：2N + 2
     * 该算法参考自ScheduledThreadPoolExecutor#DelayWorkQueue
     * @param currentIndex 插入节点的索引
     * @param element 插入节点
     */
    private void siftUp(int currentIndex, T element) {

        while (currentIndex > 0) {

            int parentIndex = (currentIndex - 1) >>> 1; //当前节点的父节点的索引
            T parentElement = data.get(parentIndex);//当前节点的父节点

            if (element.compareTo(parentElement) <= 0) {//当前节点的值小于父节点的值
                break;
            }
            //只是单纯地将父节点移动到当前节点的索引位置上，而对于当前节点来说还要进行继续比较以便确定最终的插入点，所以只是使用了父节点的索引，很精髓
            data.set(currentIndex, parentElement);
            currentIndex = parentIndex;
        }
        data.set(currentIndex, element);
    }

    /**
     * 从最大堆中移除指定节点后还能保持二叉堆的性质
     * @param element 指定节点
     */
    public void remove(T element) {
        if (element == null || data.indexOf(element) < 0) {
            return;
        }

        int removeIndex = data.indexOf(element);
        int endIndex = data.size() - 1;
        T endElement = data.get(endIndex);//获取末尾节点
        data.remove(endIndex);//同时将末尾位置先置null

        if (removeIndex != endIndex) { //若移除的节点刚好是末尾节点，则实际上咱们已经移除了
            siftDown(removeIndex, endElement);
            if (data.get(removeIndex) == endElement) { //11-12点要考虑的事情
                siftUp(removeIndex, endElement);
            }
        }
    }

    /**
     * 下沉节点以满足二叉堆的性质
     * 思路：
     * - 不管移除的是哪个节点，拿数组末尾的节点是最少成本的，因为拿该节点的值去覆盖移除节点的值来使其还是一颗完全二叉树，所以最终只要让其满足二叉堆性质就可以了
     * - 针对移除节点来说，就相当于把移除节点的位置空出来了，因为是二叉堆要满足其性质，所以就要考虑是它的子节点还是末尾的节点更适合来做移除节点的位置（最大堆/最小堆）
     * - 因为末尾元素最终都会被移动到指定位置，故而先将末尾位置置null
     *
     * 针对要移除的节点分为两种情况（最大堆）：
     * 1. 移除的节点无子节点，也就是说是叶子节点，如下步骤：
     *       11. 直接将末尾的节点的值覆盖，同时将末尾位置设置成null，因为它并无节点，所以不用考虑是否比子节点大，但是有一点要考虑是覆盖完后是否比父节点还要大
     *       12. 若比父节点还要大的话就要做上浮操作，即调用siftUp即可，最后退出
     *
     * 2. 删除的节点有子节点，如下步骤：
     *       21. 先比较两个子节点的值的大小，获取值最大的节点
     *       22. 在将值最大的节点与末尾的节点进行比较（若只有一个子节点的话，那么只能是左子节点，就直接比较大小）
     *       23. 若是末尾的节点更大的话，那么直接覆盖移除的节点的值即可，同时将末尾位置设置成null，最后退出
     *       24. 若是其子子节点更大的话，那么用值最大的节点覆盖到移除的节点的值，此时值最大的节点的位置就空出来了（相当于此时它被移除了），那么此时重复步骤12
     *
     * 假设一个节点的索引为：N，其父节点的索引为：(N - 1)/2，左子节点的索引为：2N + 1，其右子节点的索引为：2N + 2
     * 该算法参考自ScheduledThreadPoolExecutor#DelayWorkQueue
     * @param removeIndex 移除节点的索引
     * @param endElement 末尾节点
     */
    private void siftDown(int removeIndex, T endElement) {
        int size = data.size(); //
        int half = size >>> 1; //通过该值来判断移除节点是否有子节点，可以画几个例子验证下，我也不懂怎么来的，只能说写算法的人牛逼

        while (removeIndex < half) { //若满足条件则说明移除节点有子节点，不过不知道是两个子节点还是只有左子节点

            int index = (removeIndex << 1) + 1;
            T element = data.get(index);//左子节点

            int rightIndex = index + 1;
            T rightElement = data.get(rightIndex); //右子节点

            if (rightIndex < size && rightElement.compareTo(element) > 0 ) {//查看是否有右子节点，若满足条件则说明有，同时比较左右子节点的值大小
                index = rightIndex;
                element = data.get(rightIndex);//右子节点的值更大
            }

            //此时已经获取到值最大的节点，element

            if (endElement.compareTo(element) > 0) {//若满足条件条件说明末尾节点更大，直接覆盖移除节点的位置
                break;
            }

            //走到这里说明移除节点的子节点的值更大，所以用值更大的节点覆盖到移除节点的值，此时值最大的节点的位置空出来了，它就变成了要移除的节点了
            data.set(removeIndex,element);

            removeIndex = index;
        }

        data.set(removeIndex, endElement);
    }

    public List<T> getData() {
        return data;
    }
}
