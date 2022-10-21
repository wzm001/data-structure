package com.wzm.ds.tree.binary;

import com.wzm.ds.tree.TreeIterator;
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
        bst = new BinarySearchTree<>(random.nextInt(10000));
        for (int i = 0; i < 5000; i++) {
            bst.add(random.nextInt(10000));
        }
        System.out.println(bst);
    }

    @Test
    @DisplayName("检查")
    void checkBST() {
        TreeIterator<Integer> iterator = new TreeIterator<>(bst, TreeIterator.IN_ORDER_MODE);
        int last = Integer.MIN_VALUE;
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next > last) last = next;
            else throw new RuntimeException("不是二叉搜索树");
        }
    }

    @Test
    @DisplayName("传统查找")
    void originalFind() {
        CommonBinaryTree<Integer> binaryTree = (CommonBinaryTree<Integer>) bst;
        for (int i = 0; i < 1000; i++) {
            binaryTree.find(random.nextInt(10000));
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