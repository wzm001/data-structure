package com.wzm.ds.tree.binary;

import com.wzm.ds.tree.TreeNode;

/**
 * 二叉树的节点
 *
 * @author wuzhiming@itiger.com
 */
public class BinaryTreeNode<T> extends TreeNode<T> {

    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;

    public BinaryTreeNode(T value) {
        super(value);
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        super.setChild(0, leftChild);
        this.leftChild = leftChild;
        // 这样处理可以在打印时体现出右节点
        if (this.rightChild == null) {
            setRightChild(null);
        }
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
        super.setChild(1, rightChild);
        this.rightChild = rightChild;
    }
}
