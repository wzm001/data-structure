package com.wzm.algo.array;

/**
 * @author wuzhiming@itiger.com
 */
public class ReverseWords {

    public static void main(String[] args) {
        ReverseWords rw = new ReverseWords();
        String s = rw.reverseWords("  the    sky     is   blue     ");
        System.out.println(s);
    }

    public String reverseWords(String s) {
        // 1. 先将输入的字符串处理一下，去掉多余的空格（包括两边和中间的）
        char[] c = s.toCharArray();
        // 删除数组中的空格，时间复杂度O(n)
        int slow = 0, fast = 0;
        while (fast < c.length) {
            if (c[fast] != ' ' || (fast > 0 && c[fast - 1] != ' ')) { // 保留非空格的字符，以及前面紧贴着字母的空格
                c[slow++] = c[fast];
            }
            fast++;
        }
        // 上面的判断有可能在最后多出一个空格，需要处理
        if (c[slow - 1] == ' ') {
            slow -= 1;
        }
        // 空格处理完毕，新的数组为[0, slow)
        if (slow <= 0) return "";
        // 2. 反转新数组
        for (int i = 0, j = slow - 1; i < j; i++, j--) {
            char temp = c[i];
            c[i] = c[j];
            c[j] = temp;
        }
        // 3. 新数组反转完成后，再将单词反转回来
        for (int i = 0, j = 0; j <= slow; j++) {
            if (j == slow || c[j] == ' ') {
                // 遇到空格，反转[i, j)内的数据
                int k = j - 1;
                while (i < k) {
                    char temp = c[i];
                    c[i++] = c[k];
                    c[k--] = temp;
                }
                // 反转完后更新 i
                i = j + 1;
            }
        }
        // 返回结果
        return new String(c, 0, slow);
    }
}
