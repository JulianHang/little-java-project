package tech.zlia.interest.algorithm.tree.redblack;

import tech.zlia.interest.algorithm.tree.TreeNode;

/**
 * 红黑树的节点
 * @param <T> 节点数据类型
 */
public class RBTreeNode<T extends Comparable<T>> implements TreeNode<T> {

    /**
     * 节点的颜色
     */
    private boolean color;

    /**
     * 节点的值
     */
    private T data;

    /**
     * 父节点
     */
    private RBTreeNode<T> parent;

    /**
     * 左子树
     */
    private RBTreeNode<T> left;

    /**
     * 右子树
     */
    private RBTreeNode<T> right;

    /**
     * 初始化
     */
    public RBTreeNode(boolean color, T data, RBTreeNode<T> parent, RBTreeNode<T> left, RBTreeNode<T> right) {
        this.color = color;
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RBTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(RBTreeNode<T> parent) {
        this.parent = parent;
    }

    public RBTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(RBTreeNode<T> left) {
        this.left = left;
    }

    public RBTreeNode<T> getRight() {
        return right;
    }

    public void setRight(RBTreeNode<T> right) {
        this.right = right;
    }
}
