package com.wzm.ds.tree;

/**
 * ADT-Tree
 * <p>不会包含相同的值</p>
 *
 * @author wuzhiming@itiger.com
 */
public interface Tree<T> extends Iterable<TreeNode<T>> {
    // 查询操作

    /**
     * 获取当前树的根节点
     * @return 树的根节点
     */
    TreeNode<T> root();

    /**
     * 获取树的高度（也可以理解为树的层数）
     * <p>注意区分树的高度和节点的高度。节点的高度即该节点到叶子节点的最长路径，所有叶子节点的高度都为0</p>
     * <p>树的高度即树的根节点的高度+1，一个空树的高度为0，如果树只有一个根节点，那么它的高度是1</p>
     * @return 树的高度
     */
    int height();

    /**
     * 是否包含指定元素
     * @param value 指定元素
     * @return 是否包含指定元素
     */
    boolean contains(T value);

    /**
     * 查找包含指定值的节点
     * @param value 节点的值
     * @return  节点，不存在返回null
     */
    TreeNode<T> find(T value);

    /**
     * 获取树中包含的元素个数
     * @return 树中包含的元素个数
     */
    int size();

    /**
     * 判断当前树是否为空
     * @return 树是否为空
     */
    boolean isEmpty();

    /**
     * 是否是平衡树
     * <p>平衡树：任意节点的子树的高度差都小于等于1的树</p>
     * @return 是否是平衡树
     */
    boolean isBalance();

}
