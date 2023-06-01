package com.wzm.algo.list;

/**
 * 链表节点
 *
 * @author wuzhiming@itiger.com
 */
public class ListNode<T> {

    ListNode<T> next;
    T val;

    public ListNode(T val) {
        this.val = val;
    }

    public ListNode(T val, ListNode<T> next) {
        this.next = next;
        this.val = val;
    }
}
