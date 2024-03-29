package com.wzm.ds.tree.binary;

/**
 * <p>AVL树（Adelson-Velsky and Landis Tree）是计算机科学中最早被发明的自平衡二叉查找树。
 * 在AVL树中，任一节点对应的两棵子树的最大高度差为1，因此它也被称为高度平衡树。查找、插入和删除在平均和最坏情况下的时间复杂度都是O(log n)。
 * 增加和删除元素的操作则可能需要借由一次或多次树旋转，以实现树的重新平衡。</p>
 *
 * @author wuzhiming@itiger.com
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    @Override
    public AVLTreeNode<T> root() {
        return (AVLTreeNode<T>) super.root();
    }

    @Override
    public void add(T value) {
        AVLTreeNode<T> node = new AVLTreeNode<>(value);

    }

    @Override
    public boolean remove(T value) {
        return false;
    }
}
