package com.wzm.algo.list;

/**
 * 链表工具类
 *
 * @author wuzhiming@itiger.com
 */
public class Lists {

    /**
     * 判断链表是否有环，如果有环，返回环开始的节点
     * @param head  头节点
     * @return  环开始的节点，如果没有环，返回 null
     * @param <T> 元素类型
     */
    public static <T> ListNode<T> hasCycle(ListNode<T> head) {
        // 在快慢指针的基础上，判断成环的起点
        // 假设快指针走两步，慢指针走一步，当快慢指针相遇时，慢指针走了k步，快指针走了2k步
        // 此时慢指针还没有进入循环（也就是慢指针遍历的元素还没有重复）
        // 但快指针已经进入循环，比慢指针多走出的k步就是环的长度（快指针转了一圈）
        // 此时假设环的起点距离快慢指针相遇点为m，则链表起点距离环起点的距离就是 (k-m)
        // 恰好，因为环的长度为k，相遇点继续向前走 k-m 步也会到达环起点
        // 因此，只要在指针相遇时让其中一个指针回到链表起点，然后两个指针一起同步前进，相遇点就是环起点

        if (head == null) return null;
        ListNode<T> p1 = head, p2 = head;
        int c = 0;
        while (p1 != null) {
            p1 = p1.next;
            c++;
            // 必须严格限制 p1 走的步数是 p2 的 2 倍，因此这里需要先移动 p2 再进行判断
            if (c % 2 == 0) {
                p2 = p2.next;
                // 指针相遇，这个判断必须放在p2移动后判断，如果放在外面，会出现死循环
                if (p1 == p2) {
                    // 指针相遇说明有环，开始计算环起点
                    p1 = head;
                    while (p1 != p2) { // 这里可能出现死循环
                        p1 = p1.next;
                        p2 = p2.next;
                    }
                    return p1;
                }
            }
        }
        // 跳出循环说明没有环
        return null;
    }

    public static <T> ListNode<T> reverseList(ListNode<T> head) {
        if (head == null) return null;
        if (head.next == null) return head;
        ListNode<T> tail = null, cur = head;
        while (cur != null) {
            ListNode<T> next = cur.next;
            cur.next = tail;
            tail = cur;
            cur = next;
        }
        return tail;
    }

    /**
     * 按照分组反转链表.
     * @param head  链表头
     * @param group 分组数量
     * @return  反转后的链表
     * @param <T>   元素类型
     */
    public static <T> ListNode<T> reverseList(ListNode<T> head, int group) {
        return null;
    }

    public static void printList(ListNode<?> head) {
        if (head == null) return;
        ListNode<?> cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.val.toString());
            if (cur.next != null) {
                sb.append("->");
            }
            cur = cur.next;
        }
        System.out.println(sb);
    }

    public static <T> ListNode<T> buildList(T[] values) {
        if (values == null || values.length == 0) return null;
        ListNode<T> next = null;
        // 反向构建链表
        for (int i = values.length - 1; i >= 0; i--) {
            next = new ListNode<>(values[i], next);
        }
        return next;
    }

}
