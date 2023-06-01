package com.wzm.algo.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * 反向层序遍历
 *
 * 从下往上遍历，每层从左往右
 *
 * @author wuzhiming@itiger.com
 */
public class ReverseLevelOrder<T> implements BinaryTreeTraverser<T> {
    @Override
    public void travel(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        List<List<T>> levelList = new ArrayList<>();
        Queue<TreeNode<T>> queue = new LinkedList<>();
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
            levelList.add(list);
        }
        // levelList 中的层是从上往下的，需要最后组装结果
        for (int i = levelList.size() - 1; i >= 0; i--) {
            levelList.get(i).forEach(consumer);
        }
    }
}
