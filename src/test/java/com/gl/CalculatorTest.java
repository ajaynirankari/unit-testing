package com.gl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test cases for Calculator class")
class CalculatorTest {

    private Calculator calculator = null;

    @BeforeEach
    void initializeCalculator() {
        System.out.println("initializeCalculator");
        calculator = new Calculator();
    }

    @Test
    void addTestMethod() {
        int x = 10;
        int y = 5;
        int expected = 15;
        int actual = calculator.add(x, y);
        assertEquals(expected, actual);
    }

    @Test
    void addDoubleItTestMethod() {
        int x = 10;
        int y = 5;
        int expected = 30;
        int actual = calculator.doubleItAndThenAdd(x, y);
        assertEquals(expected, actual);
    }

    @RepeatedTest(10)
    void testAddMethod() {
        Random random = new Random();
        int x = random.nextInt();
        int y = random.nextInt();
        int expected = x + y;
        int actual = calculator.add(x, y);
        assertEquals(expected, actual);
    }

    @AfterEach
    void cleanUp() {
        System.out.println("cleanUp");
        calculator = null;
    }
}