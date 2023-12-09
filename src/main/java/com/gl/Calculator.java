package com.gl;

public class Calculator {

    public int powerOf2(int a) {
        int p = 2;
        for (int i = 1; i < a; i++) {
            p = p * 2;
        }
        //return Math.pow(2,a);
        return p;
    }

    public int add(int x, int y) {
        System.out.println("actual Calculator method called, x = " + x + ", y = " + y);
        return x + y;
    }

    public int doubleInputAndThenAdd(int x, int y) {
        int a = x * 2;
        int b = y * 2;
        return a + b;
    }

    public int divideBy(int x, int y) {
        return x / y;
    }

    public int division(int x, int y) {
        if (y < 1) {
            throw new IllegalArgumentException("y can not be less than 1");
        }

        if (x > 100) {
            throw new IllegalArgumentException("x can not be grater than 100");
        }

        return x / y;
    }

    void delete(int x, int y) {
        System.out.println("clear x = " + x + ", y= " + y);
    }
}
