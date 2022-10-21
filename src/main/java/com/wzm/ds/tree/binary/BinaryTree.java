package com.wzm.ds.tree.binary;

import com.wzm.ds.tree.Tree;

/**
 * 二叉树
 *
 * @author wuzhiming@itiger.com
 */
public interface BinaryTree<T> extends Tree<T> {

    // 查询操作

    @Override
    BinaryTree<T> find(T value);

    /**
     * 获取左子树
     * @return 左子树
     */
    BinaryTree<T> left();

    /**
     * 获取右子树
     * @return 右子树
     */
    BinaryTree<T> right();

    /**
     * 添加节点
     * @param element   节点
     */
    void add(T element);

    /**
     * 移除节点
     * @param element   节点
     * @return 如果节点不存在，直接返回false
     */
    boolean remove(T element);

    /**
     * 判断当前树是否是完美二叉树
     * <p>完美二叉树(Perfect binary tree)又叫满二叉树，指的是所有非叶子节点都有两个子节点，所有叶子节点都在同一层的树</p>
     * @return 当前树是否是完美二叉树
     */
    boolean isPerfect();

    /**
     * 判断当前树是否是完全二叉树
     * <p>完全二叉树(Complete binary tree)，满二叉树去掉最右边的若干个连续叶子节点后，成为完全二叉树</p>
     * <p>满二叉树一定是完全二叉树(Complete binary tree)</p>
     * @return 当前树是否是完全二叉树
     */
    boolean isCompleted();
}
