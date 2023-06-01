package com.wzm.algo.queue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 前 K 个高频元素
 * @author wuzhiming@itiger.com
 */
public class TopK {

    public static void main(String[] args) {
        TopK topK = new TopK();
        int[] re = topK.topKFrequent(new int[] {1,1,1,2,2,3}, 2);
        System.out.println(Arrays.toString(re));
    }

    public int[] topKFrequent(int[] nums, int k) {
        // 首先统计数组中元素出现的次数，时间复杂度 O(n)
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // 构建小顶堆（优先队列），长度固定为 k，当队列长度等于 k 时，再添加元素就需要先把最小的元素移除队列
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() == k) {
                // 如果当前元素比堆中的最小元素大，则替换，否则不做任何操作
                if (queue.peek().getValue() >= entry.getValue()) {
                    continue;
                }
                queue.poll(); // 移除堆顶元素
            }
            queue.offer(entry);
        }
        // 构建返回结果
        int[] result = new int[k];
        int index = 0;
        while (!queue.isEmpty()) {
            result[index++] = queue.poll().getKey();
        }
        return result;
    }
}
