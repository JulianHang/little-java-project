package tech.zlia.interest.algorithm.tree.binary;

import tech.zlia.interest.algorithm.tree.AbstractTree;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 二叉树的实现者
 * <p>抓住二叉树的主要特性就容易实现
 * <p>外部通过调用该类即可
 * <p>该类涉及到的算法主要参考了一篇文章，链接：https://blog.csdn.net/javazejian/article/details/53727333
 * <p>涉及到算法很看重递归想法，以上的这篇文章在算法上讲的不错，但是类的设计上个人觉得不怎么好！
 */
public class BinarySearchTree<T extends Comparable<T>> extends AbstractTree<T, BinaryTreeNode<T>> {


    /**
     * 判断该树是否为空
     * <p>根节点不存在则表示是空数，存在则不是
     * @return 是否为空
     */
    @Override
    public boolean isEmpty() {
        return getRoot() == null;
    }

    /**
     * 获取改树的节点个数
     * @return 树的节点个数
     */
    @Override
    public int size() {
        return size(getRoot());
    }

    /**
     * 获取树的节点个数
     * <p>该算法像汉诺塔，可参考文章：https://blog.csdn.net/javazejian/article/details/53452971
     * @param node 指定节点
     * @return 树的节点个数
     */
    private int size(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return size(node.getLeft()) + 1 + size(node.getRight());
    }

    /**
     * 获取树的高/深度
     * @return 树的高/深度
     */
    @Override
    public int height() {
        return height(getRoot());
    }

    /**
     * 获取指定节点的高/深度
     * @param node 根节点
     * @return 树的高/深度
     */
    private int height(BinaryTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int left = height(node.getLeft());
        int right = height(node.getRight());
        return left > right ? (left + 1) : (right + 1);
    }

    /**
     * 获取前序遍历的字符串
     * @return 前序遍历的字符串结果
     */
    @Override
    public String proOrder() {
        return proOrder(getRoot());
    }

    private String proOrder(BinaryTreeNode<T> node) {
        if (node == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(" ");
        sj.add(node.getData().toString());//记得空元素不能添加到树中

        //添加左子树
       String dataLeft = proOrder(node.getLeft());
       if (!"".equals(dataLeft)) {
           sj.add(dataLeft);
       }

       //添加右子树
       String dataRight = proOrder(node.getRight());
       if(!"".equals(dataRight)) {
           sj.add(dataRight);
       }

        return sj.toString();
    }

    /**
     * 获取中序遍历后的字符串结果
     * @return 中序遍历后的字符串结果
     */
    @Override
    public String medOrder() {
        return medOrder(getRoot());
    }

    private String medOrder(BinaryTreeNode<T> node) {
        if (node == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(" ");

        //添加左子树
        String dataLeft = medOrder(node.getLeft());
        if (!"".equals(dataLeft)) {
            sj.add(dataLeft);
        }

        //添加当前节点
        sj.add(node.getData().toString());

        //添加右子树
        String dataRight = medOrder(node.getRight());
        if(!"".equals(dataRight)) {
            sj.add(dataRight);
        }

        return sj.toString();
    }

    /**
     * 获取后序遍历后的字符串结果
     * @return 后序遍历后的字符串结果
     */
    @Override
    public String postOrder() {
        return postOrder(getRoot());
    }

    private String postOrder(BinaryTreeNode<T> node) {
        if (node == null) {
            return  "";
        }

        StringJoiner sj = new StringJoiner(" ");

        //添加左子树
        String dataLeft = postOrder(node.getLeft());
        if (!"".equals(dataLeft)) {
            sj.add(dataLeft);
        }

        //添加右子树
        String dataRight = postOrder(node.getRight());
        if(!"".equals(dataRight)) {
            sj.add(dataRight);
        }

        //当前节点
        sj.add(node.getData().toString());

        return sj.toString();
    }

    @Override
    public String levelOrder() {
        ArrayDeque<BinaryTreeNode> ad = new ArrayDeque<>();
        StringJoiner sj = new StringJoiner(" ");
        BinaryTreeNode<T> node = getRoot();

        while (node != null) {
            sj.add(node.getData().toString());

            if (node.getLeft() != null) {
                ad.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                ad.offer(node.getRight());
            }

            node = ad.poll();
        }

        return sj.toString();
    }

    /**
     * 在树中增加节点
     * @param  data 节点
     */
    @Override
    public void insert(T data) {
        Objects.requireNonNull(data);
        BinaryTreeNode<T> root = insert(data, getRoot());
        setRoot(root);
    }

    private BinaryTreeNode<T> insert(T data, BinaryTreeNode<T> node) {
        if (node == null) {
            node = new BinaryTreeNode<>(data, null, null);
        } else {

            if (data.compareTo(node.getData()) < 0 ) {//左子树
                BinaryTreeNode<T> leftNode = insert(data, node.getLeft());
                node.setLeft(leftNode);
            } else if (data.compareTo(node.getData()) > 0 ){//右子树
                BinaryTreeNode<T> rightNode = insert(data, node.getRight());
                node.setRight(rightNode);
            } else { //相等情况下不处理
                System.err.println("相等的节点不会被重复添加");
            }
        }
        return node;
    }

    @Override
    public void remove(T data) {
        Objects.requireNonNull(data);
        remove(data, getRoot());
    }

    /**
     *  1. 若删除的节点是叶子节点则直接删除即可
     *  2. 若删除的节点有一个孩子节点，删除后该节点后，孩子节点直接作为删除节点的父节点的左/右节点
     *  3. 若删除的节点有两个孩子节点，采用删除节点的右子树中最小的树代替要删除的节点，代替完毕后，删除该最小节点，个人对这种做法保持猜疑，但毕竟自己的算法水平有限
     *  所以目前是按照此方式实现
     */
    private BinaryTreeNode<T> remove(T data, BinaryTreeNode<T> node) {
        if (node == null) {
            return null;
        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult > 0) {
            BinaryTreeNode<T> nodeRight = remove(data, node.getRight());
            node.setRight(nodeRight);
        } else if (compareResult < 0) {
            BinaryTreeNode<T> nodeLeft = remove(data, node.getLeft());
            node.setLeft(nodeLeft);
        } else if (node.getLeft() != null && node.getRight() != null) { //情况3
            BinaryTreeNode<T> nodeOfMin = findOfMin(node.getRight()); //获取最小节点
            node.setData(nodeOfMin.getData());//直接该值，不用再改引用地址
            BinaryTreeNode<T> nodeRight = remove(node.getData(), node.getRight());
            node.setRight(nodeRight);
        } else {//情况2
            //叶子节点或只有一个孩子节点
            node = node.getLeft() != null ? node.getLeft() : node.getRight();
        }
        return node;
    }

    @Override
    public BinaryTreeNode<T> findNode(T data) {
        Objects.requireNonNull(data);
        return findNode(data, getRoot());
    }

    private BinaryTreeNode<T> findNode(T data, BinaryTreeNode<T> node) {
        if (node == null){
            return null;
        }

        if (data.compareTo(node.getData()) == 0) { //找个某个节点
            return node;
        } else if (data.compareTo(node.getData()) > 0) {
            return findNode(data, node.getRight());
        } else {
            return findNode(data, node.getLeft());
        }
    }

    @Override
    public T findOfMax() {
        Objects.requireNonNull(getRoot());
        return findOfMax(getRoot()).getData();
    }

    //找到最后一个右子树
    private BinaryTreeNode<T> findOfMax(BinaryTreeNode<T> node) {
        if (node.getRight() ==  null){
            return node;
        } else {
            return findOfMax(node.getRight());
        }
    }

    @Override
    public T findOfMin() {
        Objects.requireNonNull(getRoot());
        return findOfMin(getRoot()).getData();
    }

    //找到最后一个左子树
    private  BinaryTreeNode<T> findOfMin(BinaryTreeNode<T> node) {
        if (node.getLeft() ==  null){
            return node;
        } else {
            return findOfMin(node.getLeft());
        }
    }

    @Override
    public boolean contains(T data) {
        Objects.requireNonNull(data);
        return contains(data, getRoot());
    }

    private boolean contains(T data, BinaryTreeNode<T> node) {
        if (node == null) {
            return false;
        }

        if (data.compareTo(node.getData()) == 0) {
            return true;
        } else if (data.compareTo(node.getData()) > 0) {
            return contains(data, node.getRight());
        } else {
            return contains(data, node.getLeft());
        }
    }

    @Override
    public void clear() {
        clear(getRoot());
        setRoot(null);
    }

    private void clear(BinaryTreeNode<T> node) {
        if (node != null) {
            clear(node.getLeft());
            node.setLeft(null);

            clear(node.getRight());
            node.setRight(null);
        }
        node = null;
    }
}
