package com.wzm.algo.tree;

import java.util.function.Consumer;

/**
 * 二叉树遍历
 *
 * @author wuzhiming@itiger.com
 */
public interface BinaryTreeTraverser<T> {

    /**
     * 遍历二叉树
     * @param root  二叉树根节点
     * @param consumer  遍历操作
     */
    void travel(TreeNode<T> root, Consumer<T> consumer);
}
