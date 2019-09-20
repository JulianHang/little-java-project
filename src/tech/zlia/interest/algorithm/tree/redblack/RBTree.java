package tech.zlia.interest.algorithm.tree.redblack;

import tech.zlia.interest.algorithm.tree.AbstractTree;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.StringJoiner;

public class RBTree<T extends Comparable<T>> extends AbstractTree<T, RBTreeNode<T>> {

    private final static boolean RED = false;
    private final static boolean BLACK = true;

    @Override
    public boolean isEmpty() {
        return getRoot() == null;
    }

    @Override
    public int size() {
        return size(getRoot());
    }

    private int size(RBTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        return size(node.getLeft()) + size(node.getRight()) + 1;
    }

    @Override
    public int height() {
        return height(getRoot());
    }

    private int height(RBTreeNode<T> node) {
        if (node == null) {
            return 0;
        }
        int left = height(node.getLeft());
        int right = height(node.getRight());

        return left > right ? (left + 1) : (right + 1);

    }

    @Override
    public String proOrder() {
        return proOrder(getRoot());
    }

    private String proOrder(RBTreeNode<T> node) {
        if (node == null) {
            return "";
        }

        StringJoiner sj = new StringJoiner(".");
        sj.add(node.getData().toString());

        String leftData = proOrder(node.getLeft());
        if (!"".equals(leftData)) {
            sj.add(leftData);
        }

        String rightData = proOrder(node.getRight());
        if (!"".equals(rightData)) {
            sj.add(rightData);
        }

        return sj.toString();
    }

    @Override
    public String medOrder() {
        return medOrder(getRoot());
    }

    private String medOrder(RBTreeNode<T> node) {
        if (node == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(".");

        String leftData = medOrder(node.getLeft());
        if (!"".equals(leftData)) {
            sj.add(leftData);
        }

        sj.add(node.getData().toString());

        String rightData = medOrder(node.getRight());
        if (!"".equals(rightData)) {
            sj.add(rightData);
        }

        return sj.toString();
    }


    @Override
    public String postOrder() {
        return postOrder(getRoot());
    }

    private String postOrder(RBTreeNode<T> node) {
        if (node == null) {
            return "";
        }
        StringJoiner sj = new StringJoiner(".");

        String leftData = postOrder(node.getLeft());
        if (!"".equals(leftData)) {
            sj.add(leftData);
        }

        String rightData = postOrder(node.getRight());
        if (!"".equals(rightData)) {
            sj.add(rightData);
        }

        sj.add(node.getData().toString());

        return sj.toString();

    }

    @Override
    public String levelOrder() {
        ArrayDeque<RBTreeNode<T>> ad = new ArrayDeque<>();
        RBTreeNode<T> node = getRoot();
        StringJoiner sj = new StringJoiner(".");

        while(node != null) {

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
     * 与上面的levelOrder方法没啥区别，返回包含节点信息的队列
     */
    public ArrayDeque<RBTreeNode<T>> byLevelOrder() {
        ArrayDeque<RBTreeNode<T>> ad = new ArrayDeque<>();
        ArrayDeque<RBTreeNode<T>> adAnother = new ArrayDeque<>();
        RBTreeNode<T> node = getRoot();

        while(node != null) {

            adAnother.offer(node);

            if (node.getLeft() != null) {
                ad.offer(node.getLeft());
            }

            if (node.getRight() != null) {
                ad.offer(node.getRight());
            }

            node = ad.poll();
        }

        return adAnother;
    }

    @Override
    public void insert(T data) {
        Objects.requireNonNull(data);

        RBTreeNode<T> addNode = new RBTreeNode<>(RED, data, null, null, null);
        RBTreeNode<T> node = getRoot();

        if (node == null) {
            setRoot(addNode);
        } else {
            RBTreeNode<T> parent = null;

            while (node != null) {
                parent = node;
                if (data.compareTo(node.getData()) < 0) { //左侧
                    node = node.getLeft();
                } else if (data.compareTo(node.getData()) > 0) {
                    node = node.getRight();
                } else {
                    System.err.println("相等的节点不会被重复添加");
                    break;
                }
            }
            addNode.setParent(parent);
            //比较应该放在parent的左子树还是右子树上
            if (data.compareTo(parent.getData()) < 0) {
                parent.setLeft(addNode);
            } else {
                parent.setRight(addNode);
            }
        }
        insertAdjust(addNode);
    }

    /**
     * 将X定义为新插入的节点
     */
    private void insertAdjust(RBTreeNode<T> node) {
        if (node == getRoot()) {
            /**
             * 若X是根节点，将其变成黑色即可
             */
            node.setColor(BLACK);
        } else {

            if (node != null && node.getParent() != null) {

                /**
                 * 若X的父节点是黑色，则不需要任何的操作
                 */
                //BLACK为true
                if (node.getParent().isColor()) {
//                    System.out.println("删除节点的父节点是黑色，不需要任何的操作");
                } else {
                    //判空
                    if (node.getParent().getParent() != null) {

                        RBTreeNode<T> siblingNode; //X的父节点的兄弟节点
                        RBTreeNode<T> fatherNode = node.getParent(); //X的父节点
                        RBTreeNode<T> grandFatherNode = fatherNode.getParent(); //X的爷爷节点

                        //不管X的父节点在左子树上还是右子树上，调用的方法内容都是一致的，所以这里我们先获取X的父节点的兄弟节点，然后在接着判断红色还是黑色
                        if (fatherNode == grandFatherNode.getLeft()) { //X的父节点在左子树上
                            siblingNode = grandFatherNode.getRight();
                        } else { //X的父节点在右子树上
                            siblingNode = grandFatherNode.getLeft();
                        }

                        if (siblingNode == null || siblingNode.isColor()) {//X的父节点的兄弟节点是黑色，有可能为空

                            /**
                             * 若X的父节点是红色，叔叔节点是黑色，X和X的父节点在X的爷爷节点的左子树上（左左情况）
                             * 若X的父节点是红色，叔叔节点是黑色，X和X的父节点在X的爷爷节点的右子树上（右右情况）
                             *
                             * 以上的两种情况都是同一个处理办法
                             * 1. 将X的父节点与X的爷爷节点进行颜色互换（其实也可以猜到，既然X的父节点是红色，那么X的爷爷节点必然是黑色，所以互换也相当于直接设置父节点为黑色，爷爷节点为红色，两种方案都可以）
                             * 2. 将X的爷爷节点进行左/右旋，左左情况是右旋，右右情况是左旋
                             */

                            /**
                             * 若X的父节点在X的爷爷节点的左子树上，X在X的父节点的右子树上（左右情况）
                             * 若X的父节点在X的爷爷节点的右子树上，X在X的父节点的左子树上（右左情况）
                             *
                             * 以上的两种情况都是同一个处理办法
                             * 1. 将X的父节点进行左/右旋，左右情况是左旋，右左情况是右旋
                             * 下面的处理情况与上面的左左/右右情况是一样的
                             * 注意旋转后，发现情况出现了和上面左左/右左情况是一样的， 所以X的父节点将当作新插入的节点继续执行
                             */

                            //判断X的父节点在哪一侧
                            if (fatherNode == grandFatherNode.getLeft()) {//X的父节点在左子树上

                                //判断X在哪一侧
                                if (node == fatherNode.getLeft()) { //左左情况
                                    boolean tempColor = fatherNode.isColor();
                                    fatherNode.setColor(grandFatherNode.isColor());
                                    grandFatherNode.setColor(tempColor);
                                    rightRotate(grandFatherNode);
                                } else {//左右情况
                                    leftRotate(fatherNode);
                                    insertAdjust(fatherNode);
                                }

                            } else {

                                if (node == fatherNode.getLeft()) { //右左
                                    rightRotate(fatherNode);
                                    insertAdjust(fatherNode);
                                } else {//右右
                                    boolean tempColor = fatherNode.isColor();
                                    fatherNode.setColor(grandFatherNode.isColor());
                                    grandFatherNode.setColor(tempColor);
                                    leftRotate(grandFatherNode);
                                }
                            }

                        } else {//X的父节点的兄弟节点是红色
                            /**
                             * 若X的父节点是红色，叔叔节点（隶属于同一个父节点）也是红色
                             * 1. 将X的父节点与叔叔节点变成黑色
                             * 2. 将X的爷爷节点变成红色
                             * 3. X的爷爷节点变成红色后，有可能会出现连续红色节点的冲突，若有的话则将X的爷爷节点当作是新插入的节点，继续重复41、42操作，直到当前节点是根节点，最后将根节点变成黑色。
                             */
                            fatherNode.setColor(BLACK);
                            siblingNode.setColor(BLACK);
                            grandFatherNode.setColor(RED);
                            insertAdjust(grandFatherNode);
                        }
                    }
                }
            } else {
                System.err.println("父节点为空，请检查错误");
            }
        }
    }

    /**
     * 左旋
     */
    private void leftRotate(RBTreeNode<T> node) {
        if (node != null && node.getRight() != null ) {
            RBTreeNode<T> rightNode = node.getRight();

            RBTreeNode<T> subRightNode = rightNode.getLeft();
            if (subRightNode != null) {
                node.setRight(subRightNode);
                subRightNode.setParent(node);
            } else {
                node.setRight(null);
            }

            RBTreeNode<T> parentNode = node.getParent();
            rightNode.setParent(parentNode);
            if (parentNode == null) {//若node的父亲是空，则设为root节点
                setRoot(rightNode);
            } else if (node == parentNode.getLeft()){
                parentNode.setLeft(rightNode);
            } else {
                parentNode.setRight(rightNode);
            }
            rightNode.setLeft(node);
            node.setParent(rightNode);
        }
    }

    /**
     * 右旋
     */
    private void rightRotate(RBTreeNode<T> node) {
        if (node != null && node.getLeft() != null) {
            RBTreeNode<T> leftNode = node.getLeft();

            RBTreeNode<T> subLeftNode = leftNode.getRight();
            if (subLeftNode != null) {
                node.setLeft(subLeftNode);
                subLeftNode.setParent(node);
            } else {
                node.setLeft(null);
            }

            RBTreeNode<T> parentNode = node.getParent();
            leftNode.setParent(parentNode);
            if (parentNode == null) {
                setRoot(leftNode);
            } else if (node == parentNode.getRight()){
                parentNode.setRight(leftNode);
            } else {
                parentNode.setLeft(leftNode);
            }
            leftNode.setRight(node);
            node.setParent(leftNode);
        }
    }

    @Override
    public void remove(T data) {
        Objects.requireNonNull(data);

        RBTreeNode<T> deleteNode = findNode(data);
        if (deleteNode != null) {

            RBTreeNode<T> deleteSiblingNode = null;//X的兄弟节点
            RBTreeNode<T> deleteParentNode = deleteNode.getParent();//X的父节点

            //判断X有无子节点
            if (deleteNode.getLeft() != null && deleteNode.getRight() != null) {

                RBTreeNode<T> minOfNode = findOfMin(deleteNode.getRight());
                T tempData = deleteNode.getData();
                deleteNode.setData(minOfNode.getData());
                minOfNode.setData(tempData);

                remove(minOfNode.getData());
            } else if (deleteNode.getLeft() != null || deleteNode.getRight() != null) {

                if (deleteNode.isColor() && (!deleteParentNode.getLeft().isColor() || !deleteParentNode.getRight().isColor())) {

                    deleteNode.setParent(null);
                    if (deleteNode == deleteParentNode.getLeft()) {
                        deleteParentNode.setLeft(null);
                    } else {
                        deleteParentNode.setRight(null);
                    }

                    RBTreeNode<T> subDeleteNode = deleteNode.getLeft() != null ? deleteNode.getLeft() : deleteNode.getRight();
                    subDeleteNode.setParent(deleteParentNode);

                    if (deleteNode.getLeft() != null) {
                        deleteNode.getLeft().setColor(BLACK);
                        deleteParentNode.setLeft(subDeleteNode);
                        deleteNode.setLeft(null);
                    } else {
                        deleteNode.getRight().setColor(BLACK);
                        deleteParentNode.setRight(subDeleteNode);
                        deleteNode.setRight(null);
                    }
                }

            } else { //X无子节点

                if (deleteNode.isColor()) {

                    //获取兄弟节点
                    if (deleteNode == deleteParentNode.getLeft()) {
                        deleteSiblingNode = deleteParentNode.getRight();
                    } else {
                        deleteSiblingNode = deleteParentNode.getLeft();
                    }

                    //判空而已
                    if (deleteSiblingNode != null) {

                        //判断兄弟节点有无子节点
                        if (deleteSiblingNode.getLeft() != null && deleteSiblingNode.getRight() != null) {//X的兄弟节点有两个子节点

                            if (deleteSiblingNode.isColor()) {//X的兄弟节点为黑色

                                /**
                                 * X没有子节点，X为黑色，X的兄弟节点有两个子节点，X的兄弟节点为黑色，那么它的两个子节点必定都是红色
                                 * 1. 删除X
                                 * 2. X的父节点进行左/右旋，X在左子树是左旋，X在右子树是右旋
                                 * 3. 将X的父节点与X的父节点的左/右子节点变成黑色，X在左子树上就将右子节点变成黑色，X在右子树上就将左子节点变成黑色
                                 * 4. 若X的兄弟节点是根节点则不需要变化，将X的兄弟节点变成红色
                                 */
                                deleteNode.setParent(null);
                                if (deleteNode == deleteParentNode.getLeft()) {
                                    deleteParentNode.setLeft(null);

                                    leftRotate(deleteParentNode);
                                    deleteParentNode.setColor(BLACK);
                                    deleteSiblingNode.getRight().setColor(BLACK);

                                    if (deleteSiblingNode != getRoot()) {
                                        deleteSiblingNode.setColor(RED);
                                    }

                                } else {
                                    deleteParentNode.setRight(null);

                                    rightRotate(deleteParentNode);
                                    deleteParentNode.setColor(BLACK);
                                    deleteSiblingNode.getLeft().setColor(BLACK);

                                    if (deleteSiblingNode != getRoot()) {
                                        deleteSiblingNode.setColor(RED);
                                    }
                                }
                            } else {//X的兄弟节点为红色

                                //记录X在左边还是右边
                                String flag;
                                deleteNode.setParent(null);
                                if (deleteNode == deleteParentNode.getLeft()) {
                                    deleteParentNode.setLeft(null);
                                    flag = "left";
                                } else {
                                    deleteParentNode.setRight(null);
                                    flag = "right";
                                }

                                /**
                                 * X的兄弟节点的子节点都无子节点（只关注与X同一边的节点）
                                 * 1. 删除X
                                 * 2. X的父节点与X的兄弟节点颜色互换
                                 * 3. X的父节点左/右旋，X在左子树是左旋，X在右子树是右旋
                                 * 4. X的父节点与X的父节点的子节点（旋转过后的子节点）颜色互换
                                 */
                                if (("left".equals(flag) && deleteSiblingNode.getLeft().getLeft() == null && deleteSiblingNode.getLeft().getRight() == null) || ("right".equals(flag) && deleteSiblingNode.getRight().getLeft() == null && deleteSiblingNode.getRight().getRight() == null)) {
                                    boolean tempColor = deleteParentNode.isColor();
                                    deleteParentNode.setColor(deleteSiblingNode.isColor());
                                    deleteSiblingNode.setColor(tempColor);

                                    if ("left".equals(flag)) {//X在左子树上
                                        leftRotate(deleteParentNode);
                                        boolean color = deleteParentNode.isColor();
                                        deleteParentNode.setColor(deleteParentNode.getRight().isColor());
                                        deleteParentNode.getRight().setColor(color);
                                    } else {
                                        rightRotate(deleteParentNode);
                                        boolean color = deleteParentNode.isColor();
                                        deleteParentNode.setColor(deleteParentNode.getLeft().isColor());
                                        deleteParentNode.getLeft().setColor(color);
                                    }
                                } else if (("left".equals(flag) && (deleteSiblingNode.getLeft().getLeft() != null || deleteSiblingNode.getLeft().getRight() !=null) || ("right".equals(flag) && (deleteSiblingNode.getRight().getLeft() != null || deleteSiblingNode.getRight().getRight() != null)))) {

                                    /**
                                     * X的兄弟节点的子节点只有一个子节点，X的兄弟节点的子节点为黑色，那么X的兄弟节点的子节点的子节点必为红色
                                     * 1. 删除X
                                     * 2. x的父节点左/右旋，x在左子树是左旋，x在右子树右旋
                                     * 3. x的父节点与X的兄弟节点颜色互换
                                     * 4. 若X的父节点的左/右子树（旋转过后）的左/右子树与X同边，简单来说，若X在左/右子树，那么只考虑X的父节点的右/左子树的左/右子树，先从5开始执行，若是没对应上则先从7开始执行，同理X在右子树上
                                     * 5. X在左/右子树，X的父节点的右/左子树进行右/左旋
                                     * 6. X在左/右子树，X的父节点的右/左子树与X的父节点的右/左子树的右/左子树进行颜色互换
                                     * 7. X的父节点左/右旋，x在左子树是左旋，x在右子树右旋
                                     * 8. X的父节点与X的父节点的兄弟节点变成黑色，X的父节点的父节点变成红色（旋转过后）
                                     */
                                    if ("left".equals(flag)) {
                                        leftRotate(deleteParentNode);
                                    } else {
                                        rightRotate(deleteParentNode);
                                    }

                                    boolean tempColor = deleteParentNode.isColor();
                                    deleteParentNode.setColor(deleteSiblingNode.isColor());
                                    deleteSiblingNode.setColor(tempColor);

                                    if ("left".equals(flag)) {
                                        if (deleteParentNode.getRight().getLeft() != null) {
                                            rightRotate(deleteParentNode.getRight());

                                            boolean color = deleteParentNode.getRight().isColor();
                                            deleteParentNode.getRight().setColor(deleteParentNode.getRight().getRight().isColor());
                                            deleteParentNode.getRight().getRight().setColor(color);
                                        }
                                        leftRotate(deleteParentNode);
                                    } else {
                                        if (deleteParentNode.getLeft().getRight() != null) {
                                            leftRotate(deleteParentNode.getLeft());

                                            boolean color = deleteParentNode.getLeft().isColor();
                                            deleteParentNode.getLeft().setColor(deleteParentNode.getLeft().getLeft().isColor());
                                            deleteParentNode.getLeft().getLeft().setColor(color);
                                        }
                                        rightRotate(deleteParentNode);
                                    }

                                    deleteParentNode.setColor(BLACK);
                                    if (deleteParentNode == deleteParentNode.getParent().getLeft()) {
                                        deleteParentNode.getParent().getRight().setColor(BLACK);
                                    } else {
                                        deleteParentNode.getParent().getLeft().setColor(BLACK);
                                    }

                                } else if (("left".equals(flag) && deleteSiblingNode.getLeft().getLeft() !=null && deleteSiblingNode.getLeft().getRight() != null) || ("right".equals(flag) && deleteSiblingNode.getRight().getLeft() != null && deleteSiblingNode.getRight().getRight() != null)) {
                                    /**
                                     * X的兄弟节点的子节点有两个子节点，X的兄弟节点的子节点为黑色，那么X的兄弟节点的子节点的两个子节点都为红色
                                     * 1. 删除X
                                     * 2. x的父节点左/右旋，x在左子树是左旋，x在右子树右旋
                                     * 3. x的父节点与X的兄弟节点颜色互换
                                     * 4. x的父节点左/右旋，x在左子树是左旋，x在右子树右旋
                                     * 5. X的父节点与X的父节点的兄弟节点变成黑色，X的父节点的父节点变成红色（旋转过后）
                                     */
                                    if ("left".equals(flag)) {
                                        leftRotate(deleteParentNode);
                                    } else {
                                        rightRotate(deleteParentNode);
                                    }

                                    boolean tempColor = deleteParentNode.isColor();
                                    deleteParentNode.setColor(deleteSiblingNode.isColor());
                                    deleteSiblingNode.setColor(tempColor);

                                    if ("left".equals(flag)) {
                                        leftRotate(deleteParentNode);
                                    } else {
                                        rightRotate(deleteParentNode);
                                    }

                                    deleteParentNode.getParent().setColor(RED);
                                    if (deleteParentNode == deleteParentNode.getParent().getLeft()) {
                                        deleteParentNode.getParent().getRight().setColor(BLACK);
                                    } else {
                                        deleteParentNode.getParent().getLeft().setColor(BLACK);
                                    }
                                }

                            }

                        } else if (deleteSiblingNode.getLeft() != null || deleteSiblingNode.getRight() != null) {//X的兄弟节点只有一个子节点

                            /**
                             * X没有子节点，X为黑色，X的兄弟节点只有一个子节点
                             *
                             * 若X的兄弟节点与X的兄弟节点的子节点在左子树上（左左情况）
                             * 若X的兄弟节点与X的兄弟节点的子节点在右子树上（右右情况）
                             *
                             * 以上的两种情况都是同一个处理办法
                             * 1. 删除X
                             * 2. 将X的父节点与X的兄弟节点进行颜色互换
                             * 3. 将X的父节点变成黑色
                             * 4. 将X的兄弟节点的子节点变成黑色
                             * 5. 将X的父节点进行左/右旋，左左情况是右旋，右右情况是左旋
                             *
                             * 若X的兄弟节点在左子树上，X的兄弟节点的子节点在右子树上（左右情况）
                             * 若X的兄弟节点在右子树上，X的兄弟节点的子节点在左子树上（右左情况）
                             *
                             * 以上的两种情况都是同一个处理办法
                             * 1. 删除X
                             * 2. 将X的兄弟节点与X的兄弟节点的子节点进行颜色互换
                             * 3. 将X的兄弟节点进行左/右旋，左右情况是左旋，右左情况是右旋
                             * 4. 下列的步骤与`133`下的步骤是一样的
                             * 下面的处理情况与上面的左左/右右情况是一样的
                             *  注意旋转后，发现情况出现了和上面左左/右左情况是一样的， 所以X的父节点将当作新的删除节点继续执行
                             */
                            if (deleteSiblingNode == deleteParentNode.getLeft()) {

                                deleteParentNode.setRight(null);
                                deleteNode.setParent(null);

                                if (deleteSiblingNode.getLeft() != null) { //左左情况

                                    boolean tempColor = deleteParentNode.isColor();
                                    deleteParentNode.setColor(deleteSiblingNode.isColor());
                                    deleteSiblingNode.setColor(tempColor);

                                    deleteParentNode.setColor(BLACK);
                                    deleteSiblingNode.getLeft().setColor(BLACK);

                                    rightRotate(deleteParentNode);

                                } else {//左右情况

                                    boolean tempColor = deleteSiblingNode.isColor();
                                    deleteSiblingNode.setColor(deleteSiblingNode.getRight().isColor());
                                    deleteSiblingNode.getRight().setColor(tempColor);

                                    leftRotate(deleteSiblingNode);

                                    rightRotate(deleteParentNode);
                                    deleteParentNode.setColor(BLACK);
                                    deleteSiblingNode.setColor(BLACK);
                                    deleteParentNode.getParent().setColor(RED);
                                }

                            } else {

                                deleteParentNode.setLeft(null);
                                deleteNode.setParent(null);

                                if (deleteSiblingNode.getLeft() != null) {//右左情况

                                    boolean tempColor = deleteSiblingNode.isColor();
                                    deleteSiblingNode.setColor(deleteSiblingNode.getLeft().isColor());
                                    deleteSiblingNode.getLeft().setColor(tempColor);

                                    rightRotate(deleteSiblingNode);

                                    leftRotate(deleteParentNode);
                                    deleteParentNode.setColor(BLACK);
                                    deleteSiblingNode.setColor(BLACK);
                                    deleteParentNode.getParent().setColor(RED);

                                } else {//右右情况

                                    boolean tempColor = deleteParentNode.isColor();
                                    deleteParentNode.setColor(deleteSiblingNode.isColor());
                                    deleteSiblingNode.setColor(tempColor);

                                    deleteParentNode.setColor(BLACK);
                                    deleteSiblingNode.getRight().setColor(BLACK);

                                    leftRotate(deleteParentNode);
                                }
                            }

                        } else {
                            /**
                             * X没有子节点，X为黑色，X的兄弟节点没有子节点
                             * 1. 那么X的兄弟节点肯定也为黑色，不然就违背了任意路径黑色数目不一致的情况
                             * 2. 删除X
                             * 3. 将X的兄弟节点变成红色，X的父节点变成黑色
                             */
                            if (deleteNode == deleteParentNode.getLeft()) {
                                deleteParentNode.setLeft(null);
                            } else {
                                deleteParentNode.setRight(null);
                            }
                            deleteNode.setParent(null);

                            deleteSiblingNode.setColor(RED);
                            deleteParentNode.setColor(BLACK);
                        }
                    }

                } else {
                    /**
                     * X为红色，不需要任何处理，直接删除
                     */
                    if (deleteNode == deleteParentNode.getLeft()) {
                        deleteParentNode.setLeft(null);
                    } else {
                        deleteParentNode.setRight(null);
                    }
                    deleteNode.setParent(null);
                }
            }
        }

    }

    @Override
    public RBTreeNode<T> findNode(T data) {
        return findNode(data, getRoot());
    }

    private RBTreeNode<T> findNode(T data, RBTreeNode<T> node) {
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
        return findOfMax(getRoot()).getData();
    }

    private RBTreeNode<T> findOfMax(RBTreeNode<T> node) {
        if (node.getRight() == null) {
            return node;
        } else {
            return findOfMax(node.getRight());
        }
    }

    @Override
    public T findOfMin() {
        return findOfMin(getRoot()).getData();
    }

    private RBTreeNode<T> findOfMin(RBTreeNode<T> node) {
        if (node.getRight() == null) {
            return node;
        } else {
            return findOfMin(node.getRight());
        }
    }

    @Override
    public boolean contains(T data) {
        return contains(data, getRoot());
    }

    private boolean contains(T data, RBTreeNode<T> node) {
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

    private void clear(RBTreeNode<T> node) {
        if (node != null) {
            clear(node.getLeft());
            node.setLeft(null);

            clear(node.getRight());
            node.setRight(null);
        }
        node = null;
    }
}
