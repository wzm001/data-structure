package com.wzm.ds.tree.binary;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonBinaryTreeTest {

    BinaryTree<Integer> binaryTree;

    @BeforeEach
    void init() {
        binaryTree = CommonBinaryTree.levelBuild(new Integer[] {1,2,3,4,5,6,7,8});
        System.out.println(binaryTree);
    }

    @Test
    void isLeaf() {
        assertFalse(binaryTree.find(1).isLeaf());
        assertTrue(binaryTree.find(8).isLeaf());
    }

    @Test
    void left() {
        assertEquals(2, binaryTree.find(1).left().value());
        assertEquals(3, binaryTree.find(1).right().value());
        assertNull(binaryTree.find(5).left());
    }

    @Test
    void isPerfect() {
        assertFalse(binaryTree.isPerfect());
        binaryTree.add(9);
        binaryTree.add(10);
        binaryTree.add(11);
        binaryTree.add(12);
        binaryTree.add(13);
        binaryTree.add(14);
        binaryTree.add(15);
        assertTrue(binaryTree.isPerfect());
        assertTrue(binaryTree.isCompleted());
        assertTrue(binaryTree.isBalance());
    }

    @Test
    void isCompleted() {
        assertTrue(binaryTree.isCompleted());
        CommonBinaryTree<Integer> node = (CommonBinaryTree<Integer>) binaryTree.find(2);
        node.right = null;
        assertFalse(binaryTree.isCompleted());
    }

    @Test
    void depth() {
        assertEquals(3, binaryTree.depth(8));
        assertEquals(2, binaryTree.depth(4));
        assertEquals(1, binaryTree.depth(2));
        assertEquals(0, binaryTree.depth(1));
    }

    @Test
    void isBalance() {
        assertTrue(binaryTree.isBalance());
        CommonBinaryTree<Integer> node = (CommonBinaryTree<Integer>) binaryTree.find(2);
        node.right = null;
        assertFalse(binaryTree.isBalance());
    }
}