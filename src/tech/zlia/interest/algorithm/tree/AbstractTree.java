package tech.zlia.interest.algorithm.tree;

/**
 * 抽象树，定义树的特性
 * <p>每棵树都有一个根节点
 */
public abstract class AbstractTree<T extends Comparable<T>, U extends TreeNode> implements Tree<T, U>{

    /**
     * 根节点
     */
    private U root;

    public U getRoot() {
        return root;
    }

    public void setRoot(U root) {
        this.root = root;
    }
}
