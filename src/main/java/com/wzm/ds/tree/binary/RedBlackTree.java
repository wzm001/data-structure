package com.wzm.ds.tree.binary;

/**
 * 红黑树（英语：Red–black tree）是一种自平衡二叉查找树，是在计算机科学中用到的一种数据结构，典型用途是实现关联数组
 * <p>红黑树和AVL树一样都对插入时间、删除时间和查找时间提供了最好可能的最坏情况担保</p>
 * <p>红黑树相对于AVL树来说，牺牲了部分平衡性以换取插入/删除操作时少量的旋转操作，整体来说性能要优于AVL树</p>
 * @author wuzhiming@itiger.com
 */
public class RedBlackTree <T extends Comparable<T>> extends BinarySearchTree<T> {
}
