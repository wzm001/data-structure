package com.wzm.ds.tree.binary;

import com.wzm.ds.tree.TreeIterator;
import com.wzm.ds.tree.TreeNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private BinaryTree<Integer> bst;

    @BeforeEach
    void init() {
        bst = new BinarySearchTree<>();
        bst.add(66);
        bst.add(6);
        bst.add(12);
        bst.add(50);
        bst.add(15);
        bst.add(68);
        bst.add(64);
        bst.add(99);
        bst.add(19);
        bst.add(6);
        bst.add(94);
        bst.add(58);
        bst.add(19);
        bst.add(37);
        bst.add(47);
        bst.add(94);
        bst.add(40);
        bst.add(97);
        bst.add(17);
        bst.add(83);
    }

    @Test
    @DisplayName("检查")
    void checkBST() {
        TreeIterator<Integer> iterator = new TreeIterator<>(bst, TreeIterator.IN_ORDER_MODE);
        int last = Integer.MIN_VALUE;
        while (iterator.hasNext()) {
            TreeNode<Integer> next = iterator.next();
            assertTrue(next.getValue() > last);
            last = next.getValue();
        }
    }

    @Test
    @DisplayName("查找")
    void find() {
        assertNotNull(bst.find(12));
        assertNotNull(bst.find(66));
        assertNotNull(bst.find(40));
        assertNull(bst.find(null));
        assertNull(bst.find(123));
    }

    @Test
    @DisplayName("删除")
    void remove() {
        System.out.println(bst);
        assertTrue(bst.remove(19));
        checkBST();
        System.out.println("19 removed: ");
        System.out.println(bst);
        assertEquals(16, bst.size());
        assertTrue(bst.remove(66));
        checkBST();
        System.out.println("66 removed: ");
        System.out.println(bst);
        assertEquals(15, bst.size());
        checkBST();
    }
}