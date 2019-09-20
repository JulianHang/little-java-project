package tech.zlia.interest.algorithm.tree.redblack;

import java.text.MessageFormat;
import java.util.ArrayDeque;

/**
 * 测试红黑树
 */
public class TestFeature {

    public static void main(String[] args) {

//        RBTree<Integer> rbt = new RBTree<>();
//        rbt.insert(90);
//        rbt.insert(50);
//        rbt.insert(120);
//        rbt.insert(20);
//        rbt.insert(70);
//        rbt.insert(100);
//        rbt.insert(150);
//        rbt.insert(10);
//        rbt.insert(30);
//
//        ArrayDeque<RBTreeNode<Integer>> ad = rbt.byLevelOrder();
//
//        //我们定义的黑色为true
//        ad.forEach(v -> {
//            System.out.println(MessageFormat.format("data：【{0}】   color：【{1}】", v.getData(), v.isColor()));
//        });

        RBTree<Integer> rbtAnother = new RBTree<>();
        rbtAnother.insert(90);
        rbtAnother.insert(60);
        rbtAnother.insert(110);
        rbtAnother.insert(30);
        rbtAnother.insert(80);
        rbtAnother.insert(10);

        rbtAnother.remove(110);

        ArrayDeque<RBTreeNode<Integer>> adAnother = rbtAnother.byLevelOrder();

        //我们定义的黑色为true
        adAnother.forEach(v -> {
            System.out.println(MessageFormat.format("data：【{0}】   color：【{1}】", v.getData(), v.isColor()));
        });

    }
}
/* 结果展示

data：【90】   color：【true】
data：【50】   color：【false】
data：【120】   color：【true】
data：【20】   color：【true】
data：【70】   color：【true】
data：【100】   color：【false】
data：【150】   color：【false】
data：【10】   color：【false】
data：【30】   color：【false】

*/