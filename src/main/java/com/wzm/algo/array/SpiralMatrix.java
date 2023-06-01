package com.wzm.algo.array;

import java.util.LinkedList;
import java.util.List;

/**
 * 螺旋矩阵1（https://leetcode.cn/problems/spiral-matrix/）
 *
 * @author wuzhiming@itiger.com
 */
public class SpiralMatrix {

    public static void main(String[] args) {
        int[][] matrix = new int[1][3];
        matrix[0] = new int[] {6,9,7};

        SpiralMatrix sm = new SpiralMatrix();
        List<Integer> list = sm.spiralOrder(matrix);
        System.out.println(list);
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        // 抓住循环过程中的不变量，在遍历矩阵的 4 条边（上、右、下、左）时，统一按照前闭后开进行处理
        // 右上角的元素属于右边，左下角的元素属于左边
        // 一次循环转一圈。假设第一次循环上边有 m 个元素，下一次循环上边就有 m-2 个元素,
        // 直到元素个数小于 1，打印结束

        // 定义每次循环开始时横坐标 x 和纵坐标 y 的值，从左上角开始
        int startX = 0, startY = 0; // x 是内层数组的指针，y 是外层数组的指针

        // 定义每条边打印的元素个数，每次循环完后个数-2
        int limitX = matrix[0].length - 1, limitY = matrix.length - 1;

        List<Integer> result = new LinkedList<>();

        while (limitX > 0 && limitY > 0) { // 定义外层循环条件。这里要注意如果二维数组某一维长度为1，不会进入循环
            // 开始打印
            int x = startX, y = startY;

            // 上。y不变，x递增，打印 matrix[y]，从 x 开始，打印 limitX 个元素
            int limit = limitX;
            while (limit > 0) {
                result.add(matrix[y][x++]);
                limit--;
            }

            // 右。x不变，y递增，打印 matrix[y][x]，从 y 开始，打印 limitY 个元素
            limit = limitY;
            while (limit > 0) {
                result.add(matrix[y++][x]);
                limit--;
            }

            // 下。y不变，x递减
            limit = limitX;
            while (limit > 0) {
                result.add(matrix[y][x--]);
                limit--;
            }

            // 左。
            limit = limitY;
            while (limit > 0) {
                result.add(matrix[y--][x]);
                limit--;
            }

            // 打印完成，调整坐标和变量
            limitX -= 2;
            limitY -= 2;
            startX += 1;
            startY += 1;
        }
        // 补偿缺失的元素（维数组某一维长度为1，导致limit==0）
        // 方法走到这里，最多只能有一个 limit > 0
        if (limitX > 0) {
            // 横坐标还没打印完
            while (limitX > 0) {
                result.add(matrix[startY][startX++]);
                limitX--;
            }
        } else if (limitY > 0) {
            // 纵坐标还没打印完
            while (limitY > 0) {
                result.add(matrix[startY++][startX]);
                limitY--;
            }
        }

        return result;
    }
}
