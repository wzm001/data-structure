package com.wzm.ds.tree.binary;

/**
 * AVL树的节点
 *
 * @author wuzhiming@itiger.com
 */
public class AVLTreeNode<T extends Comparable<T>> extends BinaryTreeNode<T> {

    /**
     * 以当前节点为根节点的树的高度
     */
    protected int height;

    public AVLTreeNode(T value) {
        super(value);
        this.height = 1;
    }

    @Override
    public AVLTreeNode<T> getParent() {
        return (AVLTreeNode<T>) parent;
    }

    @Override
    public AVLTreeNode<T> getLeftChild() {
        return (AVLTreeNode<T>) super.leftChild;
    }

    @Override
    public AVLTreeNode<T> getRightChild() {
        return (AVLTreeNode<T>) super.rightChild;
    }

    /**
     * 平衡因子：节点的左子树高度 - 节点的右子树高度
     * <p>AVL树要求节点的平衡因子绝对值不能大于1</p>
     * @return 平衡因子
     */
    public int getBalanceFactor() {
        return heightOfRoot(getLeftChild()) - heightOfRoot(getRightChild());
    }

    private static int heightOfRoot(AVLTreeNode<?> root) {
        if (root == null) return 0;
        return root.height;
    }

    public void setLeftChild(AVLTreeNode<T> leftChild) {
        super.setLeftChild(leftChild);
        // 向上回溯路径，更新路径上的节点高度值
        flushNodeHeight(this);
    }

    private static void flushNodeHeight(AVLTreeNode<?> node) {
        while (node != null) {
            node.height = Math.max(heightOfRoot(node.getLeftChild()), heightOfRoot(node.getRightChild())) + 1;
            node = node.getParent();
        }
    }

    public void setRightChild(AVLTreeNode<T> rightChild) {
        super.setRightChild(rightChild);
        // 向上回溯路径，更新路径上的节点高度值
        flushNodeHeight(this);
    }
}
