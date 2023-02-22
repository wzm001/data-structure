package com.wzm.ds.tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonTreeTest {

    static Tree<String> commonTree;

    @BeforeAll
    static void init() {
        CommonTree<String> tree = new CommonTree<>();
        tree.size = 10;
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
        c.setChild(2, f);

        TreeNode<String> g = new TreeNode<>("G");
        TreeNode<String> h = new TreeNode<>("H");
        g.setChild(0, h);

        tree.root().setChild(0, b);
        tree.root().setChild(1, c);
        tree.root().setChild(2, g);

        commonTree = tree;

        System.out.println(commonTree);
    }

    @Test
    @DisplayName("高度")
    void height() {
        assertEquals(4, commonTree.height());
    }

    @Test
    @DisplayName("节点数量")
    void size() {
        assertEquals(10, commonTree.size());
    }

    @Test
    @DisplayName("包含")
    void contains() {
        assertTrue(commonTree.contains("A"));
        assertTrue(commonTree.contains("B"));
        assertTrue(commonTree.contains("C"));
        assertTrue(commonTree.contains("D"));
        assertTrue(commonTree.contains("E"));
        assertTrue(commonTree.contains("F"));
        assertTrue(commonTree.contains("G"));
        assertTrue(commonTree.contains("H"));
        assertTrue(commonTree.contains("I"));
        assertTrue(commonTree.contains("J"));
        assertFalse(commonTree.contains(null));
        assertFalse(commonTree.contains("null"));
    }

    @Test
    @DisplayName("遍历")
    void iterator() {
        TreeIterator<String> preOrder = new TreeIterator<>(commonTree, TreeIterator.PRE_ORDER_MODE);
        assertEquals("ABCDEIJFGH", iterate(preOrder));
        TreeIterator<String> postOrder = new TreeIterator<>(commonTree, TreeIterator.POST_ORDER_MODE);
        assertEquals("BDIJEFCHGA", iterate(postOrder));
        TreeIterator<String> levelOrder = new TreeIterator<>(commonTree, TreeIterator.LEVEL_ORDER_MODE);
        assertEquals("ABCGDEFHIJ", iterate(levelOrder));
    }

    private String iterate(TreeIterator<String> iterator) {
        StringBuilder sb = new StringBuilder();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getValue());
        }
        return sb.toString();
    }

    @Test
    @DisplayName("平衡树判断")
    void isBalance() {
        assertFalse(commonTree.isBalance());
    }
}