package com.wzm.ds.tree;

import com.wzm.ds.list._List;

/**
 * ADT-Tree
 * <p>要求不能包含空值和相同的值</p>
 * <p>树的节点也视为一棵树，即该树的子树</p>
 *
 * @author wuzhiming@itiger.com
 */
public interface Tree<T> extends Iterable<T> {
    // 查询操作

    /**
     * 获取根节点的高度，根节点的高度即树的高度（树的高度也称为树的深度）
     * <p>节点的高度即该节点到叶子节点的最长路径，所有叶子节点的高度都为0</p>
     * @return 根节点的高度
     */
    int height();

    /**
     * 获取指定节点相对于当前树的根节点的深度
     * <p>从根节点到该节点的唯一路径长度，根节点的深度为0</p>
     *
     * @param node 指定的节点值
     * @return  该节点的深度
     */
    int depth(T node);

    /**
     * 是否包含指定元素
     * @param value 指定元素
     * @return 是否包含指定元素
     */
    boolean contains(T value);

    /**
     * 查找子树，该子树的根节点值等于给定的值
     * @param value 子树的根节点的值
     * @return  子树的根节点，不存在返回null
     */
    Tree<T> find(T value);

    /**
     * 获取树中包含的元素个数
     * @return 树中包含的元素个数
     */
    int size();

    /**
     * 获取当前树的所有子树
     * <p>注意：该list的size不代表子树的个数，需要遍历list获取到不为空的元素，这样处理主要是为了兼容二叉树的场景，因为节点的子树有特定顺序，不能随意变动</p>
     * @return 当前树的所有子树
     */
    _List<Tree<T>> subTrees();

    /**
     * 判断当前树是否是叶子节点（即没有任何子树的节点）
     * @return 是否是叶子节点
     */
    boolean isLeaf();

    /**
     * 获取当前树根节点的值
     * @return 当前树根节点的值
     */
    T value();

    /**
     * 是否是平衡树
     * <p>平衡树：任意节点的子树的高度差都小于等于1的树</p>
     * @return 是否是平衡树
     */
    boolean isBalance();
}
