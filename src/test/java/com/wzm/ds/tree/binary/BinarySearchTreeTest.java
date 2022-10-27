package com.wzm.ds.tree.binary;

import com.wzm.ds.tree.TreeIterator;
import com.wzm.ds.tree.TreeNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    private static BinaryTree<Integer> bst;
    private static Random random;

    @BeforeAll
    static void init() {
        random = new Random(System.currentTimeMillis());
        bst = new BinarySearchTree<>();
        for (int i = 0; i < 50; i++) {
            int value = random.nextInt(100);
            bst.add(value);
        }
        System.out.println(bst);
        // 遍历
        TreeIterator<Integer> iterator = new TreeIterator<>(bst, TreeIterator.IN_ORDER_MODE);
        while (iterator.hasNext()) {
            System.out.print(iterator.next().getValue() + ",");
        }
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
        for (int i = 0; i < 1000; i++) {
            bst.find(random.nextInt(10000));
        }
    }

    @Test
    void remove() {
    }
}