package com.wzm.ds.list;

import java.util.Iterator;
import java.util.Objects;

/**
 * 链表实现
 *
 * @author wuzhiming@itiger.com
 */
public class _LinkedList<E> implements _List<E> {

    private int size;
    protected ListNode<E> head;
    protected ListNode<E> tail;

    public _LinkedList(E[] elements) {
        if (elements != null && elements.length > 0) {
            for (E element : elements) {
                add(element);
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E target) {
        return indexOf(target) > -1;
    }

    @Override
    public int indexOf(E target) {
        int index = -1;
        for (E e : this) {
            index++;
            if (Objects.equals(e, target)) return index;
        }
        return -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new _LinkedListIterator<>(head);
    }

    @Override
    public E get(int index) {
        return getNode(index).value;
    }

    private ListNode<E> getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        ListNode<E> cur;
        if (index > size / 2) {
            // 从尾部向前遍历
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        } else {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        }
        return cur;
    }

    @Override
    public void add(E element) {
        ListNode<E> node = new ListNode<>();
        size++;
        node.value = element;
        if (head == null) {
            head = node;
            tail = node;
            return;
        }
        node.prev = tail;
        tail.next = node;
        tail = node;
    }

    @Override
    public void set(int index, E element) {
        getNode(index).value = element;
    }

    @Override
    public E remove(int index) {
        ListNode<E> node = getNode(index);
        if (node == head) {
            head = head.next;
            if (head != null) head.prev = null;
        } else if (node == tail) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
        size--;
        return node.value;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (E e : this) {
            sb.append(e.toString()).append(",");
        }
        return sb.replace(sb.length() - 1, sb.length(), "]").toString();
    }

    public static class ListNode<E> {
        E value;
        ListNode<E> next;
        ListNode<E> prev;

        public E getValue() {
            return value;
        }
    }

    private final static class _LinkedListIterator<E> implements Iterator<E> {

        private ListNode<E> cur;

        public _LinkedListIterator(ListNode<E> cur) {
            this.cur = cur;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            E v = cur.value;
            cur = cur.next;
            return v;
        }
    }
}
