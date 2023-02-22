package com.wzm.ds.tree.binary;

import com.wzm.ds.tree.CommonTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 普通二叉树，没有数据和结构上的特殊约束
 *
 * @author wuzhiming@itiger.com
 */
abstract class CommonBinaryTree<T extends Comparable<T>> extends CommonTree<T> implements BinaryTree<T> {

    @Override
    public BinaryTreeNode<T> root() {
        return (BinaryTreeNode<T>) super.root();
    }

    @Override
    public BinaryTreeNode<T> find(T value) {
        return (BinaryTreeNode<T>) super.find(value);
    }

    @Override
    public boolean isPerfect() {
        // 完美二叉树的节点数量 n = 2 ^ (树的层数) - 1
        int expectedNodes = (int) (Math.pow(2, height()) - 1);
        return expectedNodes == size();
    }

    @Override
    public boolean isCompleted() {
        if (isEmpty()) return true;
        // 完全二叉树如果按照层序遍历，叶子节点的空节点也算上，则最终所有的空节点都是连续的
        Queue<BinaryTreeNode<T>> queue = new LinkedList<>();
        queue.offer(root());
        boolean meetNull = false;
        while (!queue.isEmpty()) {
            BinaryTreeNode<T> node = queue.poll();
            if (node != null) {
                if (meetNull) {
                    // 如果之前已经遍历到null，说明不是完全二叉树
                    return false;
                }
                // 如果之前还没有遍历到null，继续遍历
                queue.offer(node.getLeftChild());
                queue.offer(node.getRightChild());
            } else {
                meetNull = true;
            }
        }
        return true;
    }

}
