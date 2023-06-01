package com.wzm.algo.array;

/**
 * @author wuzhiming@itiger.com
 */
public class StringToInteger {

    public static void main(String[] args) {
        StringToInteger sti = new StringToInteger();
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(sti.myAtoi("-21474836df"));
    }

    public int myAtoi(String s) {
        s = s.trim();
        if (s.length() == 0) return 0;
        // 判断首字符是否合法
        if (!Character.isDigit(s.charAt(0)) && '+' != s.charAt(0) && '-' != s.charAt(0)) {
            return 0;
        }
        // 判断数字的正负号
        boolean neg = '-' == s.charAt(0);
        long ans = 0L; // 保存结果，因为可能会溢出，所以用 long 保存
        int i = Character.isDigit(s.charAt(0)) ? 0 : 1; // 遍历的起点
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            ans = ans * 10 + (s.charAt(i++) - '0');
            if (!neg && ans > Integer.MAX_VALUE) {
                ans = Integer.MAX_VALUE;
                break;
            }
            if (neg && ans > 1L + Integer.MAX_VALUE) {
                ans = 1L + Integer.MAX_VALUE;
                break;
            }
        }
        return neg ? (int) -ans : (int) ans;
    }
}
