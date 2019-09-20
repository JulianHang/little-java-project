package tech.zlia.interest.algorithm.tree.binary;


import tech.zlia.interest.algorithm.tree.TreeNode;

/**
 * 二叉树中节点的实现者
 */
public class BinaryTreeNode<T extends Comparable<T>> implements TreeNode<T>{

    /**
     * 定义数据类型
     */
    private T data;

    /**
     * 节点的左子树
     */
    private BinaryTreeNode<T> left;

    /**
     * 节点的右子树
     */
    private BinaryTreeNode<T> right;

    /**
     * 初始化构造
     *
     * @param data  数据
     * @param left  左子树
     * @param right 右子树
     */
    public BinaryTreeNode(T data, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }
}
