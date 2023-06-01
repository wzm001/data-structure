package com.wzm.algo.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * 前序遍历
 *
 * @author wuzhiming@itiger.com
 */
public class PreOrderTraverser<T> implements BinaryTreeTraverser<T> {
    @Override
    public void travel(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 取出栈顶元素，进行操作，同时按照 右-左 （这样出栈的顺序就是 左-右）的顺序将子节点压入栈中
            TreeNode<T> node = stack.pop();
            consumer.accept(node.value);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
}
