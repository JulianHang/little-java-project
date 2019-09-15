package tech.zlia.interest.algorithm.tree.avl;

import tech.zlia.interest.algorithm.tree.AbstractTree;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * 平衡二叉树-avl
 * <p>该类涉及到的算法主要参考了一篇文章，链接：https://blog.csdn.net/javazejian/article/details/53892797
 * @version - 1.0.0 2019-09-12
 */
public class AvlBalanceTree<T extends Comparable<T>> extends AbstractTree<T, AvlTreeNode<T>> {

    @Override
    public boolean isEmpty() {
        return getRoot() == null;
    }

    @Override
    public int size() {
        return size(getRoot());
    }

    int size(AvlTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return size(node.getLeft()) + 1 + size(node.getRight());
    }

    @Override
    public int height() {
        return getRoot() != null ? getRoot().getHeight() : 0;
    }

    public int height(AvlTreeNode<T> node) {
        return node != null ? node.getHeight() : -1;
    }

    @Override
    public String proOrder() {
        return proOrder(getRoot());
    }

    public String proOrder(AvlTreeNode<T> node) {
        if(node == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(",");
        sj.add(node.getData().toString());

        String dataLeft = proOrder(node.getLeft());
        if (!"".equals(dataLeft)) {
            sj.add(dataLeft);
        }

        String dataRight = proOrder(node.getRight());
        if(!"".equals(dataRight)) {
            sj.add(dataRight);
        }

        return sj.toString();
    }

    @Override
    public String medOrder() {
        return medOrder(getRoot());
    }

    String medOrder(AvlTreeNode<T> node){
        if (node == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(",");

        String dataLeft = medOrder(node.getLeft());
        if (!"".equals(dataLeft)) {
            sj.add(dataLeft);
        }

        sj.add(node.getData().toString());

        String dataRight = medOrder(node.getRight());
        if (!"".equals(dataRight)) {
            sj.add(dataRight);
        }

        return sj.toString();
    }

    @Override
    public String postOrder() {
        return postOrder(getRoot());
    }

    String postOrder(AvlTreeNode<T> node){
        if (node == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(",");

        String dataLeft = postOrder(node.getLeft());
        if (!"".equals(dataLeft)) {
            sj.add(dataLeft);
        }

        String dataRight = postOrder(node.getRight());
        if (!"".equals(dataRight)) {
            sj.add(dataRight);
        }

        sj.add(node.getData().toString());

        return sj.toString();
    }

    @Override
    public String levelOrder() {

        AvlTreeNode<T> node = getRoot();
        ArrayDeque<AvlTreeNode<T>> ad = new ArrayDeque<>();
        StringJoiner sj = new StringJoiner(",");

        while (node != null) {
            sj.add(node.getData().toString());

            if (node.getLeft() != null) {
                ad.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                ad.offer(node.getRight());
            }

            node = ad.poll();//
        }

        return sj.toString();
    }

    @Override
    public void insert(T data) {
        Objects.requireNonNull(data);
        setRoot(insert(data, getRoot()));
    }

    public AvlTreeNode<T> insert(T data, AvlTreeNode<T> node) {
        if (node == null) {
            return new AvlTreeNode<>(data, null, null, 0);
        }

        if (data.compareTo(node.getData()) < 0) {
            //由于最终添加到了左边，故最终根节点的左子树的高度应该大于右子树的高度，但是目前不知道是左左型还是左右型，后面会接着判断
            //为什么是左左 + 左右 不是 左左 + 右左？
            //若是右左的话，那么显然是根节点的右子树，最终应该是执行到 data.compareTo(dataAnother) > 0 代码里，而不是该代码
            AvlTreeNode<T> nodeLeft = insert(data, node.getLeft());
            node.setLeft(nodeLeft);//设置为左子树

            //判断当前节点的左右子树高度差值是否大于1
            if (height(node.getLeft()) - height(node.getRight()) == 2) {
                //左左型
                if (data.compareTo(node.getLeft().getData()) < 0) {
                    node = singleRotateLeft(node);
                } else { //左右型
                    node = doubleRotateWithLeft(node);
                }
            }

        } else if (data.compareTo(node.getData()) > 0) {
            AvlTreeNode<T> nodeRight = insert(data, node.getRight());
            node.setRight(nodeRight);

            if (height(node.getRight()) - height(node.getLeft()) == 2) {
                //右左型
                if (data.compareTo(node.getRight().getData()) < 0){
                    node = doubleRotateWithRight(node);
                } else {
                    node = singleRotateRight(node);
                }
            }

        } else {
            System.err.println("相等的节点不会被重复添加");
        }

        //2. 插入节点后矫正高度
        int height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
        node.setHeight(height);

        return node;
    }

    /**
     * 左左型（右旋转）
     */
    private AvlTreeNode<T> singleRotateLeft(AvlTreeNode<T> node) {
        if (node != null) {
            AvlTreeNode<T> nodeLeft = node.getLeft();
            //注意这边的顺序不能颠倒，否则会出现循环引用
            node.setLeft(nodeLeft.getRight());
            nodeLeft.setRight(node);

            //由于移动了，所以高度会有所变化，不过也只有当前节点与当前节点的左子树
            //height方法是计算当前节点的高度，不过一定要先改变结构后再计算，不然高度不准确
            int nodeHeight = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
            node.setHeight(nodeHeight);

            int leftHeight = Math.max(height(nodeLeft.getLeft()), height(nodeLeft.getRight())) + 1;
            nodeLeft.setHeight(leftHeight);

            return nodeLeft;
        } else {
            return null;
        }
    }

    /**
     * 左右型（左右旋转）
     */
    private AvlTreeNode<T> doubleRotateWithLeft(AvlTreeNode<T> node) {
        if (node != null) {
            //右右型
            AvlTreeNode<T> nodeLeft = singleRotateRight(node.getLeft());
            node.setLeft(nodeLeft);

            //左左型
            return singleRotateLeft(node);
        } else {
            return null;
        }
    }

    /**
     * 右右型（左旋转）
     */
    private AvlTreeNode<T> singleRotateRight(AvlTreeNode<T> node) {
        if (node != null) {
            AvlTreeNode<T> nodeRight = node.getRight();
            AvlTreeNode<T> nodeSubLeft = nodeRight.getLeft();

            node.setRight(nodeSubLeft);
            nodeRight.setLeft(node);

            int nodeHeight = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
            node.setHeight(nodeHeight);

            int nodeRightHeight = Math.max(height(nodeRight.getLeft()), height(nodeRight.getRight())) + 1;
            nodeRight.setHeight(nodeRightHeight);

            return nodeRight;
        } else {
            return null;
        }
    }

    /**
     * 右左型（右左旋转）
     */
    private AvlTreeNode<T> doubleRotateWithRight(AvlTreeNode<T> node) {
        if (node != null) {
            //右右型
            AvlTreeNode<T> nodeRight = singleRotateLeft(node.getRight());
            node.setLeft(nodeRight);

            //左左型
            return singleRotateRight(node);
        } else {
            return null;
        }
    }

    /**
     *  1. 若删除的节点是叶子节点则直接删除即可
     *  2. 若删除的节点有一个孩子节点，删除后该节点后，孩子节点直接作为删除节点的父节点的左/右节点
     *  3. 若删除的节点有两个孩子节点，采用删除节点的右子树中最小的树代替要删除的节点，代替完毕后，删除该最小节点，个人对这种做法保持猜疑，但毕竟自己的算法水平有限
     *  所以目前是按照此方式实现
     */
    @Override
    public void remove(T data) {
        Objects.requireNonNull(data);
        setRoot(remove(data, getRoot()));
    }

    /**
     * 删除节点后还要判断是否处于平衡，否则要进行旋转来达到平衡
     * 比如删除根节点的左子树后，左子树的节点数应该是减少了，相当于在根节点的右子树上增加了一个节点（左子树保持不变），同理，对于删除根节点的右子树也是一样的
     * 换个角度讲，根节点的左子树的节点减少了，相当于右子树的高度变高了，所以就要去降低右子树的高度
     */
    public AvlTreeNode<T> remove(T data, AvlTreeNode<T> node) {
        if (node == null || data == null || "".equals(data)) {
            return null;
        }

        if (data.compareTo(node.getData()) > 0) {
            AvlTreeNode<T> nodeRight = remove(data, node.getRight());
            node.setRight(nodeRight);

            //由于是右子树的节点被删除了，故应该是左子树比右子树高，接下来判断是左左型还是左右型
            if (height(node.getLeft()) - height(node.getRight()) == 2) {
                AvlTreeNode<T> nodeLeft = node.getLeft();

                //左左型
                if (height(nodeLeft.getLeft()) > height(nodeLeft.getRight())) {
                    node = singleRotateLeft(node);
                } else {
                    node = doubleRotateWithLeft(node);
                }
            }

        } else if (data.compareTo(node.getData()) < 0) {
            AvlTreeNode<T> nodeLeft = remove(data, node.getLeft());
            node.setLeft(nodeLeft);

            //由于是左子树的节点被删除了，故应该是右子树比左子树高，接下来判断是右右型还是右左型
            if (height(node.getRight()) - height(node.getLeft()) == 2) {

                AvlTreeNode<T> nodeRight = node.getRight();

                //右左型
                if (height(nodeRight.getLeft()) > height(nodeRight.getRight())) {
                    node = doubleRotateWithRight(node);
                } else {
                    node = singleRotateRight(node);
                }
            }

        } else if (node.getLeft() != null && node.getRight() != null){
            AvlTreeNode<T> nodeOfMin = findOfMin(node);
            node.setData(nodeOfMin.getData());
            AvlTreeNode<T> nodeRight = remove(node.getData(), node.getRight());
            node.setRight(nodeRight);
        } else {
            node = node.getLeft() != null ? node.getLeft() : node.getRight();
        }

        //重新计算高度
        if (node != null) {
            int height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
            node.setHeight(height);
        }

        return node;
    }

    @Override
    public AvlTreeNode<T> findNode(T data) {
        Objects.requireNonNull(data);
        return findNode(data, getRoot());
    }

    public AvlTreeNode<T> findNode(T data, AvlTreeNode<T> node) {
        if (node == null || data == null || "".equals(data)) {
            return null;
        }

        int compareResult = data.compareTo(node.getData());

        if (compareResult > 0) {
            return findNode(data, node.getRight());
        } else if (compareResult < 0) {
            return findNode(data, node.getLeft());
        } else {
            return node;
        }
    }

    @Override
    public T findOfMax() {
        Objects.requireNonNull(getRoot());
        return findOfMax(getRoot()).getData();
    }

    private AvlTreeNode<T> findOfMax(AvlTreeNode<T> node) {
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

    private AvlTreeNode<T> findOfMin(AvlTreeNode<T> node) {
        if (node.getLeft() ==  null){
            return node;
        } else {
            return findOfMax(node.getLeft());
        }
    }

    @Override
    public boolean contains(T data) {
        Objects.requireNonNull(data);
        return contains(data, getRoot());
    }

    private boolean contains(T data, AvlTreeNode<T> node) {
        if (node == null || data == null || "".equals(data)) {
            return false;
        }

        if (data.compareTo(node.getData()) > 0) {
            return contains(data, node.getRight());
        } else if (data.compareTo(node.getData()) < 0) {
            return contains(data, node.getLeft());
        } else {
            return true;
        }
    }

    @Override
    public void clear() {
        clear(getRoot());
        setRoot(null);
    }

    private void clear(AvlTreeNode<T> node) {
        if (node != null) {
            clear(node.getLeft());
            node.setLeft(null);

            clear(node.getRight());
            node.setRight(null);

            node.setHeight(0);
        }
        node = null;
    }
}
