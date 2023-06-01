package com.wzm.algo.tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTreeTraverserTest implements Consumer<Integer> {

    static TreeNode<Integer> root;
    StringBuilder sb;

    @BeforeAll
    static void initRoot() {
        root = Trees.buildBinaryTree(Arrays.asList(1,2,3,4,5,6,7,8));
    }

    @BeforeEach
    void initConsumer() {
        sb = new StringBuilder();
    }

    @Test
    @DisplayName("反转层序遍历")
    void reverseLevelOrderTravel() {
        ReverseLevelOrder<Integer> traverser = new ReverseLevelOrder<>();
        traverser.travel(root, this);
        assertEquals(sb.toString(), "84567231");
    }

    @Test
    @DisplayName("Z型遍历")
    void zOrderTravel() {
        ZOrderTraverser<Integer> traverser = new ZOrderTraverser<>();
        traverser.travel(root, this);
        assertEquals(sb.toString(), "13245678");
    }

    @Test
    @DisplayName("层序遍历")
    void levelOrderTravel() {
        LevelOrderTraverser<Integer> traverser = new LevelOrderTraverser<>();
        traverser.travel(root, this);
        assertEquals(sb.toString(), "12345678");
    }

    @Test
    @DisplayName("后序遍历")
    void postOrderTravel() {
        PostOrderTraverser<Integer> traverser = new PostOrderTraverser<>();
        traverser.travel(root, this);
        assertEquals(sb.toString(), "84526731");
    }

    @Test
    @DisplayName("中序遍历")
    void inOrderTravel() {
        InOrderTraverser<Integer> traverser = new InOrderTraverser<>();
        traverser.travel(root, this);
        assertEquals(sb.toString(), "84251637");
    }

    @Test
    @DisplayName("前序遍历")
    void preOrderTravel() {
        PreOrderTraverser<Integer> traverser = new PreOrderTraverser<>();
        traverser.travel(root, this);
        assertEquals(sb.toString(), "12485367");
    }

    @Override
    public void accept(Integer integer) {
        sb.append(integer);
    }
}