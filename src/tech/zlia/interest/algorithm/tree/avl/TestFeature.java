package tech.zlia.interest.algorithm.tree.avl;

/**
 * 测试平衡二叉树（avl）
 * <p>这边只展示了插入与删除功能
 */
public class TestFeature {

    public static void main(String[] args) {
        AvlBalanceTree<Integer> avlBalanceTree = new AvlBalanceTree<>();
        avlBalanceTree.insert(8);
        avlBalanceTree.insert(7);
        avlBalanceTree.insert(9);
        avlBalanceTree.insert(5);
        avlBalanceTree.insert(4);
        avlBalanceTree.insert(10);
        avlBalanceTree.insert(3);

        System.out.println("proOrder：" + avlBalanceTree.proOrder());

        avlBalanceTree.remove(10);

        System.out.println("proOrder-remove：" + avlBalanceTree.proOrder());
    }
}
/* 结果展示

proOrder：8,5,4,3,7,9,10
proOrder-remove：5,4,3,8,7,9

 */