package com.wzm.ds.tree.binary;

/**
 * 伸展树（英语：Splay Tree）是一种能够自我平衡的二叉查找树
 * <p>在伸展树上的一般操作都基于伸展操作：假设想要对一个二叉查找树执行一系列的查找操作，为了使整个查找时间更小，
 * 被查频率高的那些条目就应当经常处于靠近树根的位置。于是想到设计一个简单方法，在每次查找之后对树进行调整，把被查找的条目搬移到离树根近一些的地方。
 * 伸展树应运而生。伸展树是一种自调整形式的二叉查找树，它会沿着从某个节点到树根之间的路径，通过一系列的旋转把这个节点搬移到树根去。</p>
 * <p>它的优势在于不需要记录用于平衡树的冗余信息。</p>
 * @author wuzhiming@itiger.com
 */
public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {
}
