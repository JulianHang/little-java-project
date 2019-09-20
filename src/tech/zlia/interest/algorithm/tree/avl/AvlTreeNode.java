package tech.zlia.interest.algorithm.tree.avl;

import tech.zlia.interest.algorithm.tree.TreeNode;

/**
 * 平衡二叉树的节点
 * 因为节点之间要比较高度来判断是否失去平衡，故每个节点应该存有高度
 * 高度：从叶子节点自底向上到指定节点的长度，叶子节点高度是0
 * @param <T> 节点的数据类型
 */
public class AvlTreeNode<T extends Comparable<T>> implements TreeNode<T> {

    /**
     * 当前节点的数据
     */
    private T data;

    /**
     * 当前节点的高度
     */
    private int height;

    /**
     * 左子树
     */
    private AvlTreeNode<T> left;

    /**
     * 右子树
     */
    private AvlTreeNode<T> right;

    public AvlTreeNode(T data, AvlTreeNode<T> left, AvlTreeNode<T> right, int height) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = height;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public AvlTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(AvlTreeNode<T> left) {
        this.left = left;
    }

    public AvlTreeNode<T> getRight() {
        return right;
    }

    public void setRight(AvlTreeNode<T> right) {
        this.right = right;
    }
}
