package com.wzm.ds.list;

import com.wzm.ds.list.queue.LinkedQueue;
import com.wzm.ds.list.queue._Queue;
import com.wzm.ds.list.stack.LinkedStack;
import com.wzm.ds.list.stack._Stack;

/**
 * 线性表工具类
 *
 * @author wuzhiming@itiger.com
 */
public final class Lists {

    public static <E> _List<E> newLinkedList(E[] es) {
        return new _LinkedList<>(es);
    }

    public static boolean isEmpty(_List<?> list) {
        return list == null || list.isEmpty();
    }

    public static <E>_Stack<E> newStack() {
        return new LinkedStack<>();
    }

    public static <E>_Queue<E> newQueue() {
        return new LinkedQueue<>();
    }

    private Lists() {}
}
