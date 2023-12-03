package com.gl;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DisplayName("Test cases for Calculator class")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "2, 4, 6",
            "8, 9, 17"
    })
    void testAddWithTwoInputViaCsv(int x, int y, int z) {
        assertEquals(z, calculator.add(x, y));
    }

    @ParameterizedTest
    @MethodSource("com.gl.TestDataGenerator#addTwoInputsViaArguments")
    void testAddWithTwoInputViaArguments(int x, int y, int z, List<Integer> ls, String name) {
        System.out.println("ls = " + ls + ", name = " + name);
        assertEquals(z, calculator.add(x, y));
    }

    @ParameterizedTest
    @MethodSource("addTwoInputsGenerator")
    void testAddWithTwoInput(AddInput a) {
        assertEquals(a.e(), calculator.add(a.x(), a.y()));
    }

    static List<AddInput> addTwoInputsGenerator() {
        return List.of(
                new AddInput(1, 2, 3),
                new AddInput(2, 2, 4),
                new AddInput(3, 2, 5)
        );
    }

    record AddInput(int x, int y, int e) {
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8})
    void testPowerOf2(int a) {
        assertNotEquals(0, calculator.powerOf2(a));
    }

    @ParameterizedTest
    @MethodSource
    void testPower(int a) {
        assertNotEquals(0, calculator.powerOf2(a));
    }

    @ParameterizedTest
    @MethodSource("generateInput")
    void testPower1(int a) {
        assertNotEquals(0, calculator.powerOf2(a));
    }

    static Stream<Integer> testPower() {
        return Stream.of(2, 3, 7, 9, 22);
    }

    static Stream<Integer> generateInput() {
        return Stream.of(2, 3, 7, 9);
    }

    @Test
    @Order(1)
    void test1() {
        assertEquals(5, calculator.add(4, 1));
        assertThat(calculator.add(2, 3), is(equalTo(5)));
    }


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
    @Order(2)
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

class TestDataGenerator {
    static Stream<Arguments> addTwoInputsViaArguments() {
        return Stream.of(
                arguments(1, 2, 3, List.of(1, 4), "test1"),
                arguments(2, 2, 4, List.of(21, 24), "test2"),
                arguments(3, 2, 5, List.of(31, 34), "test3")
        );
    }
}