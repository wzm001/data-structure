package com.wzm.algo.tree;

/**
 * 二叉树节点
 * @author wuzhiming@itiger.com
 */
class TreeNode<T> {

    T value;
    TreeNode<T> left;
    TreeNode<T> right;

    TreeNode(T value) {
        this.value = value;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
