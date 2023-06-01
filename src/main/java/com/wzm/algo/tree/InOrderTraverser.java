package com.wzm.algo.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * 中序遍历
 *
 * @author wuzhiming@itiger.com
 */
public class InOrderTraverser<T> implements BinaryTreeTraverser<T> {
    @Override
    public void travel(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> cursor = root; // 定义游标
        while (cursor != null || !stack.isEmpty()) {
            if (cursor != null) {
                // 先将游标访问的左节点全部压入栈中
                stack.push(cursor);
                cursor = cursor.left;
            } else {
                // 当游标为null时，表示左节点压栈完成，开始处理中间节点（这里其实暗含着左节点处理完毕，因为当前左节点为null）
                TreeNode<T> node = stack.pop(); // 需要处理的节点
                consumer.accept(node.value);
                cursor = node.right; // 游标指向右节点
            }
        }
    }
}
