package com.wzm.algo.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * 层序遍历
 *
 * @author wuzhiming@itiger.com
 */
public class LevelOrderTraverser<T> implements BinaryTreeTraverser<T> {
    @Override
    public void travel(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 取出头节点，同时将左右节点入队
            TreeNode<T> node = queue.poll();
            consumer.accept(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }
}
