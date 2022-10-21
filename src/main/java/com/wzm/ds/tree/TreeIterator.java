package com.wzm.ds.tree;

import com.wzm.ds.list.Lists;
import com.wzm.ds.list.queue._Queue;
import com.wzm.ds.tree.binary.BinaryTree;

import java.util.Iterator;

/**
 * 树的迭代器
 * <p>通用的树结构支持前序和后续遍历，二叉树在此基础上支持中序遍历</p>
 * @author wuzhiming@itiger.com
 */
public class TreeIterator<T> implements Iterator<T> {

    public static final int PRE_ORDER_MODE = 1;
    public static final int IN_ORDER_MODE = 2;
    public static final int POST_ORDER_MODE = 3;
    public static final int LEVEL_ORDER_MODE = 4;

    private final _Queue<T> iteratorQueue = Lists.newQueue();

    public TreeIterator(Tree<T> tree, int mode) {
        switch (mode) {
            case PRE_ORDER_MODE:
                preOrderInit(tree);
                break;
            case IN_ORDER_MODE:
                if (tree instanceof BinaryTree) {
                    inOrderInit((BinaryTree<T>) tree);
                } else {
                    throw new UnsupportedOperationException("Only binary tree supported");
                }
                break;
            case POST_ORDER_MODE:
                postOrderInit(tree);
                break;
            case LEVEL_ORDER_MODE:
                levelOrderInit(tree);
                break;
        }
    }

    private void preOrderInit(Tree<T> tree) {
        if (tree == null) return;
        iteratorQueue.offer(tree.value());
        if (!tree.isLeaf()) {
            for (Tree<T> subTree : tree.subTrees()) {
                if (subTree != null) {
                    preOrderInit(subTree);
                }
            }
        }
    }

    protected void inOrderInit(BinaryTree<T> root) {
        if (root == null || root.isLeaf()) return;
        inOrderInit(root.left());
        iteratorQueue.offer(root.value());
        inOrderInit(root.right());
    }

    private void postOrderInit(Tree<T> tree) {
        if (tree == null) return;
        if (!tree.isLeaf()) {
            for (Tree<T> subTree : tree.subTrees()) {
                if (subTree != null) {
                    postOrderInit(subTree);
                }
            }
        }
        iteratorQueue.offer(tree.value());
    }

    private void levelOrderInit(Tree<T> root) {
        if (root == null) return;
        _Queue<Tree<T>> nodeQueue = Lists.newQueue();
        nodeQueue.offer(root);
        while (!nodeQueue.isEmpty()) {
            Tree<T> node = nodeQueue.pull();
            iteratorQueue.offer(node.value());
            if (!node.isLeaf()) {
                for (Tree<T> subTree : node.subTrees()) {
                    if (subTree != null) nodeQueue.offer(subTree);
                }
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !iteratorQueue.isEmpty();
    }

    @Override
    public T next() {
        return iteratorQueue.pull();
    }
}
