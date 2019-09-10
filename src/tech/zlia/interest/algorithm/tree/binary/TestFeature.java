package tech.zlia.interest.algorithm.tree.binary;

/**
 * 测试二叉树
 * @version  - 2019-9-10
 * @author - zlia
 */
public class TestFeature {

    public static void main(String[] args) {
        BinarySearchTree<Integer> sbt = new BinarySearchTree<>();
        sbt.insert(16);
        sbt.insert(10);
        sbt.insert(5);

        sbt.insert(14);
        sbt.insert(12);
        sbt.insert(11);
        sbt.insert(15);
        sbt.insert(18);
        sbt.insert(19);

        System.out.println("size："  + sbt.size());
        System.out.println("height："  + sbt.height());
        System.out.println("proOrder："  + sbt.proOrder());//前序
        System.out.println("medOrder："  + sbt.medOrder());//中序
        System.out.println("postOrder："  + sbt.postOrder());//后续
        System.out.println("levelOrder："  + sbt.levelOrder());
        System.out.println("findNode：" + sbt.findNode(14).getLeft().getData());
        System.out.println("findNode：" + sbt.findNode(14).getRight().getData());
        System.out.println("findOfMax：" + sbt.findOfMax());
        System.out.println("findOfMin：" + sbt.findOfMin());
        System.out.println("contains：" + sbt.contains(19));
        System.out.println("contains：" + sbt.contains(13));

//        sbt.clear();
    }
}

/* 结果展示

size：9
height：5
proOrder：16 10 5 14 12 11 15 18 19
medOrder：5 10 11 12 14 15 16 18 19
postOrder：5 11 12 15 14 10 19 18 16
levelOrder：16 10 18 5 14 19 12 15 11
findNode：12
findNode：15
findOfMax：19
findOfMin：5
contains：true
contains：false

*/