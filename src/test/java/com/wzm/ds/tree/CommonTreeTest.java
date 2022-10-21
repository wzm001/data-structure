package com.wzm.ds.tree;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommonTreeTest {

    static Tree<String> commonTree;

    /**
     * |-A
     *   |-B
     *   |-C
     *     |-D
     *     |-E
     *       |-I
     *       |-J
     *     |-F
     *   |-G
     *     |-H
     */
    @BeforeAll
    static void init() {
        commonTree = new CommonTree<>("A");
        Tree<String> b = new CommonTree<>("B");
        Tree<String> c = new CommonTree<>("C");
        Tree<String> d = new CommonTree<>("D");
        Tree<String> e = new CommonTree<>("E");
        Tree<String> i = new CommonTree<>("I");
        Tree<String> j = new CommonTree<>("J");
        e.subTrees().add(i);
        e.subTrees().add(j);
        Tree<String> f = new CommonTree<>("F");
        c.subTrees().add(d);
        c.subTrees().add(e);
        c.subTrees().add(f);

        Tree<String> g = new CommonTree<>("G");
        Tree<String> h = new CommonTree<>("H");
        g.subTrees().add(h);

        commonTree.subTrees().add(b);
        commonTree.subTrees().add(c);
        commonTree.subTrees().add(g);

        System.out.println(commonTree);
    }

    @Test
    @DisplayName("高度")
    void height() {
        assertEquals(3, commonTree.height());
    }

    @Test
    @DisplayName("深度")
    void depth() {
        assertEquals(0, commonTree.depth("A"));
        assertEquals(1, commonTree.depth("C"));
        assertEquals(3, commonTree.depth("I"));
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
    @DisplayName("查找")
    void find() {
        Tree<String> root = commonTree.find("A");
        assertNotNull(root);
        assertEquals("A", root.value());
        Tree<String> j = commonTree.find("J");
        assertNotNull(j);
        assertEquals("J", j.value());
        Tree<String> none = commonTree.find("null");
        assertNull(none);
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
            sb.append(iterator.next());
        }
        return sb.toString();
    }

    @Test
    @DisplayName("判断叶子节点")
    void isLeaf() {
        assertFalse(commonTree.isLeaf());
        assertTrue(commonTree.find("D").isLeaf());
    }

    @Test
    @DisplayName("平衡树判断")
    void isBalance() {
        assertFalse(commonTree.isBalance());
    }
}