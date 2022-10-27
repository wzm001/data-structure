package com.wzm.ds.list;

import com.wzm.ds.list.queue.LinkedQueue;
import com.wzm.ds.list.queue._Queue;
import com.wzm.ds.list.stack.LinkedStack;
import com.wzm.ds.list.stack._Stack;

import java.util.List;

/**
 * 线性表工具类
 *
 * @author wuzhiming@itiger.com
 */
public final class Lists {

    public static boolean isEmptyArray(Object[] array) {
        if (array == null || array.length == 0) return true;
        for (Object o : array) {
            if (o != null) return false;
        }
        return true;
    }

    public static <E> _List<E> newLinkedList(E[] es) {
        return new _LinkedList<>(es);
    }

    public static boolean isEmpty(List<?> list) {
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
