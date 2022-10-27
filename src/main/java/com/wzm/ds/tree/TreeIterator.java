package com.wzm.ds.tree;

import com.wzm.ds.tree.binary.BinaryTree;
import com.wzm.ds.tree.binary.BinaryTreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 树的迭代器
 * <p>通用的树结构支持前序和后续遍历，二叉树在此基础上支持中序遍历</p>
 * @author wuzhiming@itiger.com
 */
public class TreeIterator<T> implements Iterator<TreeNode<T>> {

    public static final int PRE_ORDER_MODE = 1;
    public static final int IN_ORDER_MODE = 2;
    public static final int POST_ORDER_MODE = 3;
    public static final int LEVEL_ORDER_MODE = 4;

    private final Queue<TreeNode<T>> iteratorQueue = new LinkedList<>();

    public TreeIterator(Tree<T> tree, int mode) {
        if (tree.isEmpty()) return;
        switch (mode) {
            case PRE_ORDER_MODE:
                preOrderInit(tree.root());
                break;
            case IN_ORDER_MODE:
                if (tree instanceof BinaryTree) {
                    inOrderInit((BinaryTreeNode<T>) tree.root());
                } else {
                    throw new UnsupportedOperationException("Only binary tree supported");
                }
                break;
            case POST_ORDER_MODE:
                postOrderInit(tree.root());
                break;
            case LEVEL_ORDER_MODE:
                levelOrderInit(tree.root());
                break;
        }
    }

    private void preOrderInit(TreeNode<T> root) {
        iteratorQueue.offer(root);
        if (!root.isLeaf()) {
            for (TreeNode<T> child : root.getChildren()) {
                if (child != null) {
                    preOrderInit(child);
                }
            }
        }
    }

    protected void inOrderInit(BinaryTreeNode<T> root) {
        if (root == null) return;
        inOrderInit(root.getLeftChild());
        iteratorQueue.offer(root);
        inOrderInit(root.getRightChild());
    }

    private void postOrderInit(TreeNode<T> root) {
        if (!root.isLeaf()) {
            for (TreeNode<T> child : root.getChildren()) {
                if (child != null) {
                    postOrderInit(child);
                }
            }
        }
        iteratorQueue.offer(root);
    }

    private void levelOrderInit(TreeNode<T> root) {
        Queue<TreeNode<T>> nodeQueue = new LinkedList<>();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            TreeNode<T> node = nodeQueue.poll();
            iteratorQueue.offer(node);
            if (!node.isLeaf()) {
                for (TreeNode<T> child : node.getChildren()) {
                    if (child != null) nodeQueue.offer(child);
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iteratorQueue.isEmpty();
    }

    @Override
    public TreeNode<T> next() {
        return iteratorQueue.poll();
    }
}
