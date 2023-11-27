package com.gl;

public class Calculator {
    public int add(int x, int y) {
        System.out.println("x = " + x + ", y = " + y);
        return x + y;
    }

    public int doubleItAndThenAdd(int x, int y) {
        int a = x * 2;
        int b = y * 2;
        return a + b;
    }
}
