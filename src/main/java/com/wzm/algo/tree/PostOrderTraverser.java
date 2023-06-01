package com.wzm.algo.tree;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * 后序遍历
 * @author wuzhiming@itiger.com
 */
public class PostOrderTraverser<T> implements BinaryTreeTraverser<T> {
    @Override
    public void travel(TreeNode<T> root, Consumer<T> consumer) {
        if (root == null) return;
        List<TreeNode<T>> nodeList = new LinkedList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            // 取出栈顶元素，进行操作，同时按照 左-右 （这样出栈的顺序就是 右-左）的顺序将子节点压入栈中
            TreeNode<T> node = stack.pop();
            nodeList.add(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        // 反转结果
        Collections.reverse(nodeList);
        nodeList.forEach(node -> consumer.accept(node.value));
    }
}
