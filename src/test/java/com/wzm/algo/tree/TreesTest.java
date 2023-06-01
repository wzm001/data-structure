package com.wzm.algo.tree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TreesTest {

    @Test
    void depth() {
        assertEquals(-1, Trees.depth(null, null));
        TreeNode<Integer> root = Trees.buildBinaryTree(Arrays.asList(1));
        assertEquals(1, Trees.depth(root, new TreeNode<>(1)));
        assertEquals(-1, Trees.depth(root, new TreeNode<>(2)));
        root = Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertEquals(2, Trees.depth(root, new TreeNode<>(2)));
        assertEquals(3, Trees.depth(root, new TreeNode<>(6)));
        assertEquals(-1, Trees.depth(root, new TreeNode<>(60)));
    }

    @Test
    void height() {
        assertEquals(0, Trees.height(null));
        TreeNode<Integer> root = Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertEquals(4, Trees.height(root));
    }

    @Test
    void buildBinaryTree() {
        TreeNode<Integer> root = Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertEquals(root.value, 1);
        assertEquals(root.left.value, 2);
        assertEquals(root.left.right.value, 5);
        assertEquals(root.right.right.value, 7);
        assertNull(root.left.right.left);
        assertNull(root.left.right.right);
        root = Trees.buildBinaryTree(Arrays.asList(1));
        assertNotNull(root);
        assertEquals(root.value, 1);
        assertNull(root.left);
        assertNull(root.right);
        root = Trees.buildBinaryTree(Arrays.asList(1, null, null, null, null, null, null, null, 2));
        assertEquals(root.value, 1);
        assertNull(root.left);
        assertNull(root.right);
    }

    @Test
    void isPerfectBinaryTree() {
        assertFalse(Trees.isPerfectBinaryTree(null));
        TreeNode<Integer> root = Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assertTrue(Trees.isPerfectBinaryTree(root));
        root = Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertFalse(Trees.isPerfectBinaryTree(root));
    }

    @Test
    void isCompletedBinaryTree() {
        assertFalse(Trees.isCompletedBinaryTree(null));
        TreeNode<Integer> root = Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        assertTrue(Trees.isCompletedBinaryTree(root));
        root = Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, null, 6, 7, 8, 9));
        assertFalse(Trees.isCompletedBinaryTree(root));
    }

    @Test
    void size() {
        assertEquals(0, Trees.size(null));
        assertEquals(9, Trees.size(Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))));
        assertEquals(6, Trees.size(Trees.buildBinaryTree(Arrays.asList(1, 2, 3, null, null, null, 7, 8, 9))));
    }

    @Test
    void print() {
        Trees.print(null);
        Trees.print(Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        Trees.print(Trees.buildBinaryTree(Arrays.asList(1, 2, 3, null, null, null, 7, 8, 9)));
    }

    @Test
    void isSymmetric() {
        assertFalse(Trees.isSymmetric(null));
        assertFalse(Trees.isSymmetric(Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))));
        assertTrue(Trees.isSymmetric(Trees.buildBinaryTree(Arrays.asList(1, 2, 2, 3, 4, 4, 3))));
        assertTrue(Trees.isSymmetric(Trees.buildBinaryTree(Arrays.asList(1, 2, 2, null, 4, 4, null))));
    }

    @Test
    void isBalanced() {
        assertTrue(Trees.isBalanced(null));
        assertTrue(Trees.isBalanced(Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))));
        assertFalse(Trees.isBalanced(Trees.buildBinaryTree(Arrays.asList(1, 2, 3, 4, null, null, null, 8, 9))));
    }
}