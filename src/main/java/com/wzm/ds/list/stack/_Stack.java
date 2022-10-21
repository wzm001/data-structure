package com.wzm.ds.list.stack;

/**
 * 栈
 *
 * @author wuzhiming@itiger.com
 */
public interface _Stack<E> {

    // 查询操作

    /**
     * 获取栈顶元素
     * @return 栈顶元素
     */
    E peek();

    /**
     * 栈的高度
     * @return 栈的高度
     */
    int size();

    /**
     * 栈是否为空
     * @return  栈是否为空
     */
    boolean isEmpty();

    // 修改操作

    /**
     * 弹出栈顶元素
     * @return 栈顶元素
     */
    E pop();

    /**
     * 向栈顶压入元素
     * @param e 新的栈顶元素
     */
    void push(E e);
}
