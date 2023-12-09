package com.gl;

public class ServiceA {

    ServiceB serviceB;

    public int serviceA(int x) {
        System.out.println("Line 1 serviceA x = " + x);
        x = x + 1;
        System.out.println("Line 2 serviceA x = " + x);
        System.out.println("Line 3 serviceB = " + serviceB);
        int result = serviceB.serviceB(x);
        System.out.println("Line 4 result = " + result);
        int output = result + x * 10;
        System.out.println("Line 4 output = " + output);
        return output;
    }
}
