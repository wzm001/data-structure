package com.wzm.algo.array;

/**
 * @author wuzhiming@itiger.com
 */
public class RepeatedSubstring {

    public static void main(String[] args) {
        RepeatedSubstring rs = new RepeatedSubstring();
        boolean result = rs.repeatedSubstringPattern("abab");
        System.out.println(result);
    }

    public boolean repeatedSubstringPattern(String s) {
        // 如果字符串满足条件，那么对应的子串应该有如下特征：
        // 1. 所有子串都相等；
        // 2. 子串的长度是原字符串长度的公约数，例如元字符串长度为 6， 那么子串的长度只能是 1，2，3
        // 3. 子串不能等于原字符串

        loop0:
        for (int i = 1; i <= s.length() / 2; i++) { // 注意子串的长度范围
            // 判断子串长度是否符合要求
            if (s.length() % i != 0) continue; // 无法整除，不处理
            boolean match = true;
            loop1:
            for (int j = 0; j < i; j++) {
                char last = s.charAt(j); // 从首字符开始比较
                loop2:
                for (int k = j; k < s.length(); k += i) {
                    if (s.charAt(k) != last) {
                        // 如果不相等，说明当前子串不满足要求，直接回到最外层循环
                        match = false;
                        break loop1;
                    }
                }
            }
            if (match) {
                return true;
            }
        }
        return false;
    }
}
