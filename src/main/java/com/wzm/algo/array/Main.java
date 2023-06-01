package com.wzm.algo.array;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author wuzhiming@itiger.com
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 构造参数
        int size = in.nextInt();
        int k = in.nextInt();
        int[] nums = new int[size];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(execute(nums, k));
    }

    /**
     * 计算数组中满足 "k-优雅数组" 的子数组数量
     * @param nums  数组
     * @param k     k 优雅阈值，如 3-优雅数组，表示数组中出现次数最多的元素一共出现了 3 次
     * @return 子数组的数量
     */
    private static int execute(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length < k) return 0;
        int count = 0;
        // Map 保存数组中的元素以及元素出现的次数
        Map<Integer, Integer> numCountMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            // i 每走一步，就要清空map
            numCountMap.clear();
            for (int j = i; j < nums.length; j++) {
                // 把当前j对应的元素添加到map中
                numCountMap.put(nums[j], numCountMap.getOrDefault(nums[j], 0) + 1);
                // 如果当前子数组长度小于 k，就不需要判断了
                if (j - i + 1 < k) {
                    continue;
                }
                if (match(numCountMap, k)) {
                    // 匹配，增加计数器
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean match(Map<Integer, Integer> numCountMap, int k) {
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            max = Math.max(max, entry.getValue());
        }
        return max >= k;
    }
}
