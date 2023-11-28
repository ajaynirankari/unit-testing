package com.gl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Test cases for Calculator class")
class CalculatorTest {

    private Calculator calculator = null;

    @BeforeAll
    static void beforeAllTestCases() {
        System.out.println(":: @BeforeAll = " + LocalDateTime.now());
    }

    @BeforeEach
    void initializeCalculator() {
        System.out.println(":: @BeforeEach = " + LocalDateTime.now());
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
        int actual = calculator.doubleInputAndThenAdd(x, y);

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

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 8, 9})
    void testParameterizedTest1(int x) {
        int expected = x + x;
        int actual = calculator.add(x, x);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 4",
            "8, 9"
    })
    void testParameterizedTest2(int x, int y) {
        int expected = x + y;
        int actual = calculator.add(x, y);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "2, 4, 6",
            "8, 9, 17",
            "5, 6, 11"
    })
    void testParameterizedTest3(int x, int y, int expected) {
        int actual = calculator.add(x, y);

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvFileSource(files = "data.csv")
    void testParameterizedTest4(int x, int y, int expected) {
        int actual = calculator.add(x, y);

        assertEquals(expected, actual);
    }

    @Test
    void divisionTest() {
        int x = 10;
        int y = 2;
        int expected = x / y;
        int actual = calculator.divideBy(x, y);

        assertEquals(expected, actual);
    }

    @Test
    void divisionTest1() {
        String expected = "/ by zero";
        Exception exception = assertThrows(
                ArithmeticException.class,
                () -> calculator.divideBy(10, 0)
        );
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void divisionTest2() {
        String expected = "y can not be less than 1";
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.division(10, 0)
        );
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void divisionTest5() {
        String expected = "x can not be grater than 100";
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.division(110, 10)
        );
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void divisionTest4() {
        int x = 10;
        int y = 2;
        int expected = x / y;
        int actual = calculator.division(x, y);

        assertEquals(expected, actual);
    }

    @AfterEach
    void cleanUp() {
        System.out.println(":: @AfterEach = " + LocalDateTime.now());
        calculator = null;
    }

    @AfterAll
    static void afterAllTestCases() {
        System.out.println(":: @AfterAll = " + LocalDateTime.now());
    }
}