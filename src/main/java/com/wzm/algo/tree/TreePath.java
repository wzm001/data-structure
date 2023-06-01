package com.wzm.algo.tree;

import java.util.Arrays;

/**
 * @author wuzhiming@itiger.com
 */
public class TreePath {

    public static void main(String[] args) {
        TreePath tp = new TreePath();
        TreeNode<Integer> root = Trees.buildBinaryTree(Arrays.asList(5,4,8,11,null,13,4,7,2,null,null,null,1));
        System.out.println(tp.hasPathSum(root, 22));
    }

    public boolean hasPathSum(TreeNode<Integer> root, int targetSum) {
        // 使用递归
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.value.equals(targetSum);
        return hasPathSum(root.left, targetSum - root.value)
                || hasPathSum(root.right, targetSum - root.value);
    }
}
