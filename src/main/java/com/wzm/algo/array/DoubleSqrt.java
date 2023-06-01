package com.wzm.algo.array;

/**
 * @author wuzhiming@itiger.com
 */
public class DoubleSqrt {

    public static void main(String[] args) {
        DoubleSqrt ds = new DoubleSqrt();
        double value = 3.1232;
        double result = ds.sqrt(value, 0.0000001);
        System.out.println(result);
        System.out.println(Math.sqrt(value));
    }

    private double sqrt(double value, double error) {
        double start = 0.0, end = value;
        while ((end - start) > error) {
            double mid = (start + end) / 2;
            if (mid * mid > value) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return start;
    }
}
