package com.gl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Mock
    Calculator calculatorMockObject;

    @Spy
    Calculator calculatorSpyMockObject;

    @InjectMocks
    ServiceA serviceAInjectMockObject;

    @Mock
    ServiceB serviceBMockObject;

    @Captor
    ArgumentCaptor<Integer> firstArgumentCaptor;

    @Captor
    ArgumentCaptor<Integer> secondArgumentCaptor;

    @Test
    void test9() {
        Calculator cal = mock(Calculator.class);
        cal.delete(30, 40);
        verify(cal).delete(firstArgumentCaptor.capture(), secondArgumentCaptor.capture());
        assertEquals(30, firstArgumentCaptor.getValue());
        assertEquals(40, secondArgumentCaptor.getValue());
    }

    @Test
    void test8() {
        Calculator cal = mock(Calculator.class);
        ArgumentCaptor<Integer> firstArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        ArgumentCaptor<Integer> secondArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        cal.delete(30, 40);
        verify(cal).delete(firstArgumentCaptor.capture(), secondArgumentCaptor.capture());
        assertEquals(30, firstArgumentCaptor.getValue());
        assertEquals(40, secondArgumentCaptor.getValue());
    }

    @Test
    void testServiceA() {
        int x = 5;
        doReturn((x + 1) * 10).when(serviceBMockObject).serviceB(x + 1);
        int actual = serviceAInjectMockObject.serviceA(x);
        assertEquals(120, actual);
    }

    @Test
    void test7() {
        doReturn(5).when(calculatorSpyMockObject).add(2, 3);
        int add1 = calculatorSpyMockObject.add(2, 3);
        assertEquals(5, add1);
    }

    @Test
    void test6() {
        doReturn(5).when(calculatorMockObject).add(2, 3);
        int add1 = calculatorMockObject.add(2, 3);
        assertEquals(5, add1);
    }

    @Test
    void test5() {
        //Calculator calculator = Mockito.mock(Calculator.class);
        Calculator calculatorActual = new Calculator();
        Calculator calculatorMockObject = mock(Calculator.class);
        Calculator calculatorSpyObject = spy(calculatorActual);

        int add = calculatorActual.add(2, 3);
        System.out.println("calculatorActual add = " + add);

        int add1 = calculatorMockObject.add(2, 3);
        System.out.println("calculatorMockObject add = " + add1);

        int add2 = calculatorSpyObject.add(2, 3);
        System.out.println("calculatorMockObject add = " + add2);

    }

    @Test
    void test4() {
        Calculator mockedCalculator = mock(Calculator.class);
        doThrow(new IllegalArgumentException("Not allowed")).when(mockedCalculator).division(0, 4);
        assertThrows(IllegalArgumentException.class, () -> mockedCalculator.division(0, 4));
    }


    @Test
    void test3() {
        Calculator mockedCalculator2 = mock(Calculator.class);

        //when(mockedCalculator2.add(3,5)).thenReturn(8);
        //when(mockedCalculator2.add(6,5)).thenReturn(11);
        int expected = 99;

        when(mockedCalculator2.add(anyInt(), anyInt())).thenReturn(expected);

        //when(mockedCalculator2.add(6,anyInt())).thenReturn(expected);

        int actual = mockedCalculator2.add(3, 5);
        assertEquals(expected, actual);

        actual = mockedCalculator2.add(6, 5);
        assertEquals(expected, actual);

        actual = mockedCalculator2.add(16, 15);
        assertEquals(expected, actual);

        actual = mockedCalculator2.add(4, 9);
        assertEquals(expected, actual);

        //verify(mockedCalculator2).add(anyInt(),anyInt());
        //verify(mockedCalculator2,times(1)).add(anyInt(),anyInt());

        verify(mockedCalculator2, times(4)).add(anyInt(), anyInt());

        //mockedCalculator2.powerOf2(4);

        verify(mockedCalculator2, never()).powerOf2(anyInt());
        verify(mockedCalculator2, atLeast(1)).add(anyInt(), anyInt());

    }


    @Test
    void test2() {
        List mockedList = mock(List.class);
        mockedList.add(2);
        mockedList.add(3);
        mockedList.add(4);

        Object o = mockedList.get(23);
        doReturn(999).when(mockedList).get(anyInt());

        verify(mockedList).get(anyInt());

        assertEquals(999, mockedList.get(0));
        assertEquals(999, mockedList.get(1));
        assertEquals(999, mockedList.get(2));
        assertEquals(999, mockedList.get(5));
        assertEquals(999, mockedList.get(8));

        assertNotNull(mockedList.get(1));
        assertNotNull(mockedList.get(2));
    }

    @Test
    void test1() {

        //List list = new ArrayList();
        List mockedList = mock(List.class);

        mockedList.add(23);
        when(mockedList.get(0)).thenReturn(78);
        when(mockedList.size()).thenReturn(1);

        System.out.println("mockedList.get(0) = " + mockedList.get(0));
        System.out.println("mockedList.size() = " + mockedList.size());

        mockedList.clear();
        mockedList.add(24);

        verify(mockedList, times(1)).add(23);
        verify(mockedList).clear();
    }

    @Test
    void test0() {

        Calculator calculator = mock(Calculator.class);
        when(calculator.add(10, 2)).thenReturn(12);

        int add = calculator.add(10, 2);
        System.out.println("add = " + add);

        verify(calculator).add(10, 2);
    }
}
