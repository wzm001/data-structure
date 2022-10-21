package com.wzm.ds.list.queue;

/**
 * 队列
 *
 * @author wuzhiming@itiger.com
 */
public interface _Queue<E> {

    /**
     * 队列是否为空
     * @return 队列是否为空
     */
    boolean isEmpty();

    /**
     * 队列长度
     * @return 队列长度
     */
    int size();

    /**
     * 获取队列头的元素
     * @return 队列头的元素
     */
    E peek();

    /**
     * 入队
     * @param e 元素
     */
    void offer(E e);

    /**
     * 出队
     * @return 元素
     */
    E pull();
}
