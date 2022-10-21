package com.wzm.ds.list.queue;

import com.wzm.ds.list._LinkedList;

/**
 * 链表实现的动态无界队列
 *
 * @author wuzhiming@itiger.com
 */
public class LinkedQueue<E> extends _LinkedList<E> implements _Queue<E> {

    public LinkedQueue() {
        super(null);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        return super.head.getValue();
    }

    @Override
    public void offer(E e) {
        super.add(e);
    }

    @Override
    public E pull() {
        if (isEmpty()) {
            throw new QueueEmptyException();
        }
        return super.remove(0);
    }
}
