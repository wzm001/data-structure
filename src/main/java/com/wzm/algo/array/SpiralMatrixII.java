package com.wzm.algo.array;

import java.util.Arrays;

/**
 * 循环矩阵 II (https://leetcode.cn/problems/spiral-matrix-ii/)
 *
 * @author wuzhiming@itiger.com
 */
public class SpiralMatrixII {

    public static int[][] generateMatrix(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must bigger then 0");
        int[][] result = new int[n][n];

        int loop = n >> 1; // 循环次数
        int value = 1; // 从 1 开始填充
        int startX = 0, startY = 0; // 定义每次循环的起始坐标，横向为 x，纵向为 y
        int count = n - 1; // 定义每次外层循环开始后，内层循环的次数，该次数会随着外层循环而递减
        while (loop > 0) {

            // 分别循环 上、右、下、左 四条边，每条边都是前闭后开区间
            // 例如当 n = 3 时，上=[(0,0),(0,1)]；右=[(0,2),(1,2)]；下=[(2,2),(2,1)]；左=[(2,0),(1,0)]

            int x = startX, y = startY; // 每次循环使用的坐标

            // 1. up，需要遍历内层数组，纵坐标不变，横坐标递增
            int temp = count;
            while (temp > 0) {
                result[y][x++] = value++;
                temp--;
            }

            // 2. right，需要遍历外层数组，横坐标不变，纵坐标递增
            temp = count;
            while (temp > 0) {
                result[y++][x] = value++;
                temp--;
            }

            // 3. bottom，需要遍历内层数组，纵坐标不变，横坐标递减
            for (int i = x; i > startX; i--) {
                result[y][i] = value++;
                x--;
            }

            // 4. left，需要遍历外层数组，横坐标不变，纵坐标递减
            for (int i = y; i > startY; i--) {
                result[i][x] = value++;
                y--;
            }

            // 5. 更新起始坐标和内外循环次数
            startX++;
            startY++;
            count -= 2;
            loop--;
        }
        // 当 n 为奇数时，需要填充中间的元素，mid = n / 2，int[mid][mid]=n*n
        if (n % 2 > 0) {
            int mid = n >> 1;
            result[mid][mid] = value;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] result = generateMatrix(5);
        for (int[] array : result) {
            System.out.println(Arrays.toString(array));
        }
    }
}
