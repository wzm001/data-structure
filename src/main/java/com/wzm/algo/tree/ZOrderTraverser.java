package com.wzm.algo.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Z型遍历
 *
 * Z 型遍历: 逐层遍历，但不是从左往右，层数从0开始，根节点为第0层，偶数层从左往右，奇数层从右往左，直到遍历到最底层结束
 *
 * @author wuzhiming@itiger.com
 */
public class ZOrderTraverser<T> implements BinaryTreeTraverser<T> {
    @Override
    public void travel(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        Queue<TreeNode<T>> queue = new LinkedList<>();
        int level = 0; // 根节点为第 0 层，偶数层不需要反转
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每次遍历一层
            // 当前层的节点数量
            int size = queue.size();
            List<T> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode<T> node = queue.poll();
                list.add(node.value);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (level % 2 != 0) {
                // 奇数层需要反转链表
                Collections.reverse(list);
            }
            // 访问
            list.forEach(consumer);
            level++;
        }
    }
}
