package com.wzm.algo.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 滑动窗口最大值
 * @author wuzhiming@itiger.com
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        MaxSlidingWindow instance = new MaxSlidingWindow();
        int[] result = instance.maxSlidingWindow(new int[]{-7, -8, 7, 5, 7, 1, 6, 0}, 4);
        System.out.println(Arrays.toString(result));
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue mQueue = new MonotonicQueue();
        int i = 0, j = 0, index = 0;
        int[] result = new int[nums.length - k + 1];
        // 初始化窗口指针
        while (j < k) { // 注意窗口范围是 [i,j)
            mQueue.offer(nums[j]);
            j++;
        }
        result[index++] = mQueue.getFirst();
        // 开始滑动窗口
        while (j < nums.length) {
            mQueue.poll(nums[i++]);
            mQueue.offer(nums[j++]);
            result[index++] = mQueue.getFirst();
        }
        return result;
    }

    /**
     * 实现一个单调递减队列
     */
    private static class MonotonicQueue {

        private Deque<Integer> deque = new LinkedList<>();

        public void offer(int value) {
            // 入队，如果队尾元素小于value，则从队尾弹出直到队尾元素大于等于 value
            while (!deque.isEmpty() && deque.getLast() < value) {
                deque.removeLast();
            }
            deque.offerLast(value);
        }

        public void poll(int value) {
            // 如果 value 等于队列头，则出队，否则不做任何操作
            if (!deque.isEmpty() && deque.getFirst() == value) {
                deque.removeFirst();
            }
        }

        public int getFirst() {
            // 获取头节点，如果队列为空会抛出异常
            return deque.getFirst();
        }

        @Override
        public String toString() {
            return deque.toString();
        }
    }
}
