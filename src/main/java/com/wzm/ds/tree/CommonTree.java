package com.wzm.ds.tree;

import com.wzm.ds.list.Lists;
import com.wzm.ds.list._List;

import java.util.Iterator;
import java.util.Objects;

/**
 * @author wuzhiming@itiger.com
 */
public class CommonTree<T> implements Tree<T> {

    protected final T value;
    protected _List<Tree<T>> subTrees = Lists.newLinkedList(null);

    public CommonTree(T value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public int height() {
        if (isLeaf()) return 0;
        int subHeight = 0;
        for (Tree<T> subTree : subTrees()) {
            if (subTree != null) {
                subHeight = Math.max(subHeight, subTree.height());
            }
        }
        return subHeight + 1;
    }

    @Override
    public int depth(T node) {
        if (node == null) return -1;
        if (node.equals(value)) return 0;
        if (isLeaf()) return -1; // 表示当前子树不包含该节点
        int subDepth = -1;
        for (Tree<T> subTree : subTrees()) {
            if (subTree != null) {
                subDepth = Math.max(subDepth, subTree.depth(node));
            }
        }
        return subDepth == -1 ? -1 : subDepth + 1;
    }

    @Override
    public boolean contains(T value) {
        return find(value) != null;
    }

    @Override
    public Tree<T> find(T value) {
        if (value == null) return null;
        if (this.value.equals(value)) return this;
        for (Tree<T> subTree : subTrees()) {
            if (subTree != null) {
                Tree<T> result = subTree.find(value);
                if (result != null) return result;
            }
        }
        return null;
    }

    @Override
    public int size() {
        if (isLeaf()) return 1;
        int size = 0;
        for (Tree<T> subTree : subTrees()) {
            if (subTree != null) size += subTree.size();
        }
        return size + 1;
    }

    @Override
    public _List<Tree<T>> subTrees() {
        return subTrees;
    }

    @Override
    public boolean isLeaf() {
        if (Lists.isEmpty(subTrees)) return true;
        boolean allNull = true;
        for (Tree<T> subTree : subTrees) {
            if (subTree != null) {
                allNull = false;
                break;
            }
        }
        return allNull;
    }

    @Override
    public T value() {
        return value;
    }

    @Override
    public boolean isBalance() {
        if (isLeaf()) return true;
        int minHeight = -1, maxHeight = -1;
        for (Tree<T> subTree : subTrees()) {
            if (subTree != null && !subTree.isBalance()) {
                return false;
            }
            int height = treeLevel(subTree);
            if (minHeight < 0) {
                // 初始化
                minHeight = height;
                maxHeight = height;
            } else {
                minHeight = Math.min(minHeight, height);
                maxHeight = Math.max(maxHeight, height);
            }
        }
        return maxHeight - minHeight < 2;
    }

    private static int treeLevel(Tree<?> tree) {
        if (tree == null) return 0; // 空树的层数为0
        if (tree.isLeaf()) return 1; // 叶子节点层数为1
        return tree.height() + 1; // 普通节点层数等于子树高+1
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator<>(this, TreeIterator.PRE_ORDER_MODE);
    }

    /**
     * ├  │  └ ─
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName())
                .append(", size=").append(size()).append(". \n");
        for (T t : this) {
            for (int i = 0; i < depth(t); i++) {
                sb.append("  ");
            }
            sb.append("|-").append(t.toString()).append("\n");
        }
        return sb.toString();
    }
}
