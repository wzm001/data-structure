package com.wzm.ds.tree;

/**
 * B树（英语：B-tree），是一种在计算机科学自平衡的树，能够保持数据有序。这种资料结构能够让查找数据、顺序访问、插入数据及删除的动作，
 * 都在对数时间内完成。B树，概括来说是一个一般化的二元搜寻树（binary search tree）一个节点可以拥有2个以上的子节点。
 * 与自平衡二叉查找树不同，B树适用于读写相对大的数据块的存储系统，例如磁盘。B树减少定位记录时所经历的中间过程，从而加快存取速度。
 * B树这种数据结构可以用来描述外部存储。这种资料结构常被应用在数据库和文件系统的实现上。
 * @author wuzhiming@itiger.com
 */
public class BTree<T extends Comparable<T>> extends CommonTree<T> {
}
