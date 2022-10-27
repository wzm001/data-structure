package com.wzm.ds.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author wuzhiming@itiger.com
 */
class CommonTreePrintTest {

    private Tree<String> commonTree;

    @BeforeEach
    void init() {
        CommonTree<String> tree = new CommonTree<>();
        tree.size = 8;
        tree.setRoot(new TreeNode<>("A"));
        TreeNode<String> b = new TreeNode<>("B");
        TreeNode<String> c = new TreeNode<>("C");
        TreeNode<String> d = new TreeNode<>("D");
        TreeNode<String> e = new TreeNode<>("E");
        TreeNode<String> i = new TreeNode<>("I");
        TreeNode<String> j = new TreeNode<>("J");
        e.setChild(0, i);
        e.setChild(1, j);
        TreeNode<String> f = new TreeNode<>("F");
        c.setChild(0, d);
        c.setChild(1, e);
        c.setChild(2, null);
        c.setChild(3, f);

        TreeNode<String> g = new TreeNode<>("G");
        TreeNode<String> h = new TreeNode<>("H");
        g.setChild(0, h);

        tree.root().setChild(0, b);
        tree.root().setChild(1, c);
        tree.root().setChild(2, g);

        commonTree = tree;
    }

    @Test
    void testPrint() {
        System.out.println(commonTree);
    }
}
