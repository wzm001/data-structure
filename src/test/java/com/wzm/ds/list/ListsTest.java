package com.wzm.ds.list;

import com.wzm.ds.list.queue.QueueEmptyException;
import com.wzm.ds.list.queue._Queue;
import com.wzm.ds.list.stack.StackEmptyException;
import com.wzm.ds.list.stack._Stack;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListsTest {

    @Test
    @DisplayName("链表")
    void newLinkedList() {
        _List<Integer> intList = Lists.newLinkedList(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(intList);
        assertFalse(intList.isEmpty());
        assertEquals(intList.size(), 5);
        assertEquals(intList.get(0), 1);
        assertEquals(intList.get(1), 2);
        assertEquals(intList.get(2), 3);
        assertEquals(intList.get(3), 4);
        assertEquals(intList.get(4), 5);
        assertTrue(intList.contains(1));
        assertTrue(intList.contains(2));
        assertTrue(intList.contains(5));
        assertFalse(intList.contains(10));
        assertFalse(intList.contains(null));

        intList.add(6);
        assertEquals(intList.size(), 6);
        assertEquals(intList.get(5), 6);
        assertTrue(intList.contains(6));

        intList.set(0, 10);
        assertTrue(intList.contains(10));
        assertEquals(intList.size(), 6);
        System.out.println(intList);

        intList.remove(0);
        assertEquals(intList.size(), 5);
        assertEquals(intList.get(0), 2);
        intList.remove(4);
        System.out.println(intList);
        assertEquals(intList.get(3), 5);

        intList.remove(0);
        intList.remove(0);
        intList.remove(0);
        intList.remove(0);
        System.out.println(intList);
        assertTrue(intList.isEmpty());
    }

    @Test
    @DisplayName("栈")
    void newStack() {
        _Stack<Integer> stack = Lists.newStack();
        assertTrue(stack.isEmpty());

        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(stack.size(), 3);
        assertEquals(stack.peek(), 3);
        System.out.println(stack);

        Integer pop = stack.pop();
        assertEquals(pop, 3);
        assertEquals(stack.peek(), 2);
        pop = stack.pop();
        assertEquals(pop, 2);
        assertEquals(stack.peek(), 1);
        pop = stack.pop();
        assertEquals(pop, 1);
        assertTrue(stack.isEmpty());

        assertThrows(StackEmptyException.class, stack::pop);
    }

    @Test
    @DisplayName("队列")
    void newQueue() {
        _Queue<Integer> queue = Lists.newQueue();
        assertTrue(queue.isEmpty());

        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        assertFalse(queue.isEmpty());
        assertEquals(4, queue.size());
        assertEquals(1, queue.peek());
        System.out.println(queue);

        Integer pull = queue.pull();
        assertEquals(pull, 1);
        assertEquals(queue.peek(), 2);
        pull = queue.pull();
        assertEquals(pull, 2);
        assertEquals(queue.peek(), 3);
        pull = queue.pull();
        assertEquals(pull, 3);
        assertEquals(queue.peek(), 4);
        pull = queue.pull();
        assertEquals(pull, 4);
        assertThrows(QueueEmptyException.class, queue::peek);
    }
}