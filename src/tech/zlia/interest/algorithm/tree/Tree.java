package tech.zlia.interest.algorithm.tree;


import java.util.Comparator;

/**
 * 代表一颗树
 * 定义了两种泛型，T表示树中的数据类型，U表示树中的节点类型
 * 这两种泛型在该树被创建时就应该已经确定好了
 * @param <T> 定义该树的数据类型
 * @param <U> 定义该树的节点类型
 */
public interface Tree<T, U> {

    /**
     * 判断该树是否为空
     * @return 树是否为空
     */
    boolean isEmpty();

    /**
     * 获取树的节点个数
     * @return 节点个数
     */
    int size();

    /**
     * 获取树的高/深度
     * @return 树的高/深度
     */
    int height();

    /**
     * 前序遍历
     * <p>节点-节点的左子树-节点的右子树
     * @return  前序遍历后的字符串
     */
    String proOrder();

    /**
     * 中序遍历
     * <p>节点的左子树-节点-节点的右子树
     * @return 中序遍历后的字符串
     */
    String medOrder();

    /**
     * 后序遍历
     * <p>节点的左子树-节点的右子树-节点
     * @return 后序遍历后的字符串
     */
    String postOrder();

    /**
     * 层次遍历
     * <p>按照树的层次自上而下遍历
     * @return 层次遍历后的字符串
     */
    String levelOrder();

    /**
     * 插入节点
     * <p>插入过程中会进行比较，看适合放在哪个节点的左右侧
     * @param  data 节点
     */
    void insert(T data);

    /**
     * 删除节点
     * @param data 节点
     */
    void remove(T data);

    /**
     * 根据某个值获取节点
     * @param data
     */
    U findNode(T data);

    /**
     * 树中的最大值
     * @return 最大值
     */
    T findOfMax();

    /**
     * 树中的最小值
     * @return 最小值
     */
    T findOfMin();

    /**
     * 是否包含指定值
     * @param data 指定值
     * @return 是否包含指定值
     */
    boolean contains(T data);

    /**
     * 清空
     */
    void clear();
}
