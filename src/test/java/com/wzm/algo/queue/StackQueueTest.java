package com.wzm.algo.queue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class StackQueueTest {

    @Test
    @DisplayName("基本功能测试")
    void basicTest() {
        StackQueue<Integer> queue = new StackQueue<>();
        assertThrows(EmptyStackException.class, queue::peek);
        queue.offer(null);
        assertNull(queue.poll());
        for (int i = 0; i < 100; i++) {
            queue.offer(i);
        }
        assertEquals(0, queue.peek());
        assertEquals(100, queue.size());
        for (int i = 0; i < 100; i++) {
            assertEquals(i, queue.poll());
        }
        assertEquals(0, queue.size());
        assertThrows(EmptyStackException.class, queue::poll);
    }

    @DisplayName("多线程测试")
    @RepeatedTest(10)
    void multiThreadTest() throws InterruptedException {
        StackQueue<Integer> queue = new StackQueue<>();
        TestThread task = new TestThread(queue);
        Thread pollThread = new Thread(task);
        pollThread.start();
        // 主线程入队列，另一个线程出队列
        for (int i = 0; i < 10000; i++) {
            queue.offer(i);
        }
        Thread.sleep(1000);
        assertFalse(task.error);
        assertEquals(0, queue.size());
    }

    private static class TestThread implements Runnable {

        private final StackQueue<Integer> queue;
        volatile boolean error;

        public TestThread(StackQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            // 如果出队列的顺序不是有序递增，说明发生多线程冲突
            int last = -1, count = 10000;
            while (!error && count > 0) {
                try {
                    int i = queue.poll();
                    count--;
                    if (i < last) {
                        error = true;
                        break;
                    } else {
                        last = i;
                    }
                } catch (Exception e) {
                }
            }
        }
    }
}