package com.wzm.ds.tree;

import com.wzm.ds.list.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 树节点
 *
 * @author wuzhiming@itiger.com
 */
public class TreeNode<T> {

    protected final T value;
    protected TreeNode<T> parent;
    protected List<TreeNode<T>> children;

    public TreeNode(T value) {
        this.value = Objects.requireNonNull(value);
    }

    /**
     * 如果节点没有任何子节点，则该节点为叶子节点
     * @return 是否为叶子节点
     */
    public boolean isLeaf() {
        return Lists.isEmpty(children);
    }

    public T getValue() {
        return value;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    protected void setChild(int index, TreeNode<T> node) {
        int capacity = index + 1;
        if (children == null) {
            children = new ArrayList<>(capacity);
        }
        if (children.size() < capacity) {
            for (int i = children.size(); i < capacity; i++) {
                children.add(null);
            }
        }
        children.set(index, node);
    }

}
