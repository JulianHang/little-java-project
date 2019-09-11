package tech.zlia.interest.algorithm.tree.binary;

import java.util.Arrays;

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

        System.out.println("=======================dividing line===================");

        String[] proStr = sbt.proOrder().split(" ");
        Integer[] proI = new Integer[proStr.length];
        for (int i = 0; i < proStr.length; i++) {
            proI[i] = Integer.parseInt(proStr[i]);
        }

        String[] medStr = sbt.medOrder().split(" ");
        Integer[] medI = new Integer[medStr.length];
        for (int i = 0; i < medI.length; i++) {
            medI[i] = Integer.parseInt(medStr[i]);
        }

        String[] postStr = sbt.postOrder().split(" ");
        Integer[] postI = new Integer[postStr.length];
        for (int i = 0; i < postI.length; i++) {
            postI[i] = Integer.parseInt(postStr[i]);
        }


        BinarySearchTree<Integer> sbtAnother = new BinarySearchTree<>();
//        前序 + 中序
//        BinaryTreeNode<Integer> root = sbtAnother.createBinaryTreeByProMedOrder(proI, medI, 0, proI.length - 1, 0, medI.length - 1);
//        后序 + 中序
        BinaryTreeNode<Integer> root = sbtAnother.createBinaryTreeByPostMedOrder(postI, medI, 0, proI.length - 1, 0, medI.length - 1);

        System.out.println("proOrder：" + sbt.proOrder(root));
        System.out.println("medOrder：" + sbt.medOrder(root));
        System.out.println("postOrder：" + sbt.postOrder(root));

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
=======================dividing line===================
proOrder：16 10 5 14 12 11 15 18 19
medOrder：5 10 11 12 14 15 16 18 19

*/