package com.wzm.algo.list;

import org.junit.jupiter.api.Test;

class ListsTest {

    @Test
    void reverseList() {
        ListNode<Integer> head = Lists.buildList(new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9});
        Lists.printList(head);
        ListNode<Integer> newHead = Lists.reverseList(head);
        Lists.printList(newHead);
    }

    @Test
    void reverseListWithGroup() {

    }
}