package com.wzm.ds.list.stack;

import com.wzm.ds.list._LinkedList;

/**
 * 链表实现的动态栈
 *
 * @author wuzhiming@itiger.com
 */
public class LinkedStack<E> extends _LinkedList<E> implements _Stack<E> {

    public LinkedStack() {
        super(null);
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("stack is empty");
        }
        return super.tail.getValue();
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new StackEmptyException();
        }
        return super.remove(size() - 1);
    }

    @Override
    public void push(E e) {
        super.add(e);
    }
}
