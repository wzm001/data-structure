package com.wzm.algo.array;

/**
 * @author wuzhiming@itiger.com
 */
public class Fibonacci {

    public static void main(String[] args) {
        Fibonacci fibonacci = new Fibonacci();
        System.out.println(fibonacci.get(0));
        System.out.println(fibonacci.get(1));
        System.out.println(fibonacci.get(2));
        System.out.println(fibonacci.get(3));
        System.out.println(fibonacci.get(4));
        System.out.println(fibonacci.get(5));
        System.out.println(fibonacci.get(6));
    }

    private int get(int n) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        if (n == 1) return 1;
        int fib = 0, a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            fib = a + b;
            a = b;
            b = fib;
        }
        return fib;
    }
}
