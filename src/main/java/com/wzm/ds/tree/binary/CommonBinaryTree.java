package com.wzm.ds.tree.binary;

import com.wzm.ds.list.Lists;
import com.wzm.ds.list._List;
import com.wzm.ds.list.queue._Queue;
import com.wzm.ds.tree.CommonTree;
import com.wzm.ds.tree.Tree;

/**
 * 普通二叉树，没有数据和结构上的特殊约束
 *
 * @author wuzhiming@itiger.com
 */
public class CommonBinaryTree<T> extends CommonTree<T> implements BinaryTree<T> {

    protected CommonBinaryTree<T> parent;
    protected CommonBinaryTree<T> left;
    protected CommonBinaryTree<T> right;

    protected CommonBinaryTree(T value) {
        super(value);
    }

    public static <T> BinaryTree<T> levelBuild(T[] elements) {
        BinaryTree<T> root = null;
        for (T element : elements) {
            if (root == null) {
                root = new CommonBinaryTree<>(element);
                continue;
            }
            root.add(element);
        }
        return root;
    }

    @Override
    public BinaryTree<T> find(T value) {
        Tree<T> result = super.find(value);
        if (result != null) {
            return (BinaryTree<T>) result;
        }
        return null;
    }

    @Override
    public _List<Tree<T>> subTrees() {
        _List<Tree<T>> trees = Lists.newLinkedList(null);
        trees.add(left);
        trees.add(right);
        return trees;
    }

    @Override
    public int depth(T node) {
        CommonBinaryTree<T> ts = (CommonBinaryTree<T>) find(node);
        if (ts == null) return -1;
        int depth = 0;
        while (ts.parent != null) {
            ts = ts.parent;
            depth++;
        }
        return depth;
    }

    @Override
    public boolean isLeaf() {
        return left == null && right == null;
    }

    @Override
    public BinaryTree<T> left() {
        return left;
    }

    @Override
    public BinaryTree<T> right() {
        return right;
    }

    @Override
    public void add(T element) {
        if (element == null) return;
        // 普通二叉树，使用层序添加
        _Queue<CommonBinaryTree<T>> queue = Lists.newQueue();
        queue.offer(this);
        while (!queue.isEmpty()) {
            CommonBinaryTree<T> node = queue.pull();
            if (node.innerAdd(element)) {
                break;
            }
            queue.offer(node.left);
            queue.offer(node.right);
        }
    }

    private boolean innerAdd(T element) {
        if (this.value.equals(element)) return true;
        if (this.left == null) {
            CommonBinaryTree<T> node = new CommonBinaryTree<>(element);
            node.parent = this;
            this.left = node;
            return true;
        }
        if (this.right == null) {
            CommonBinaryTree<T> node = new CommonBinaryTree<>(element);
            node.parent = this;
            this.right = node;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPerfect() {
        // 完美二叉树的节点数量 n = 2 ^ (树的层数) - 1
        int expectedNodes = (int) (Math.pow(2, height() + 1) - 1);
        return expectedNodes == size();
    }

    @Override
    public boolean isCompleted() {
        // 完全二叉树如果按照层序遍历，叶子节点的空节点也算上，则最终所有的空节点都是连续的
        _Queue<BinaryTree<T>> queue = Lists.newQueue();
        queue.offer(this);
        boolean meetNull = false;
        while (!queue.isEmpty()) {
            BinaryTree<T> node = queue.pull();
            if (node != null) {
                if (meetNull) {
                    // 如果之前已经遍历到null，说明不是完全二叉树
                    return false;
                }
                // 如果之前还没有遍历到null，继续遍历
                queue.offer(node.left());
                queue.offer(node.right());
            } else {
                meetNull = true;
            }
        }
        return true;
    }


}
