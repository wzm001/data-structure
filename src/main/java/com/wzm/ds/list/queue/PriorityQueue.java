package com.wzm.ds.list.queue;

/**
 * 优先队列
 * todo 用堆实现
 * @author wuzhiming@itiger.com
 */
public class PriorityQueue<E extends Comparable<E>> implements _Queue<E> {
    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void offer(E e) {

    }

    @Override
    public E pull() {
        return null;
    }
}
