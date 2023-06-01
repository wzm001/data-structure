package com.wzm.algo.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wuzhiming@itiger.com
 */
public class MinWindow {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        MinWindow mw = new MinWindow();
        String re = mw.minWindow(s, t);
        System.out.println(re);
    }

    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }
        // 获取最小子串，考虑使用滑动窗口的方法解决
        // 声明 Map，记录字符和出现的次数
        Map<Character, Integer> charCount = new HashMap<>();
        // 初始化目标字符串对应的Map
        Map<Character, Integer> targetCharCount = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            targetCharCount.put(t.charAt(i), targetCharCount.getOrDefault(t.charAt(i), 0) + 1);
        }
        // String result = null;
        // 改为记录起始索引和子串长度，节省计算量
        int start = -1, length = 0;
        // char[] cArray = s.toCharArray(); // 去掉 char Array，直接调用 String 的函数
        // 定义闭区间窗口
        for (int i = 0, j = 0; j < s.length(); j++) {
            // 添加字符
            char c = s.charAt(j);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            while (match(charCount, targetCharCount)) { // 如果当前子串满足条件，就缩小窗口，直到不再满足要求
                // 如果子串长度小于上一次的结果，更新上次的结果
                int len = j - i + 1;
                if (start < 0 || length > len) {
                    start = i;
                    length = len;
                }

                // 缩小窗口
                c = s.charAt(i);
                charCount.put(c, charCount.get(c) - 1);
                if (charCount.get(c) <= 0) {
                    charCount.remove(c);
                }
                i++;
            }
        }
        return start < 0 ? "" : s.substring(start, start + length);
    }

    // 判断 target 是否匹配 source
    // 如果 target 所有的 key 都包含在 source 中，且 source 中 key 对应的 value 大于 target 中的 value，则满足匹配条件
    private boolean match(Map<Character, Integer> source, Map<Character, Integer> target) {
        for (Map.Entry<Character, Integer> entry : target.entrySet()) {
            int count = source.getOrDefault(entry.getKey(), 0);
            if (count < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
