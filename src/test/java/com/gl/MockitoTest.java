package com.gl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockitoTest {

    Calculator mockedCalculator_ = mock(Calculator.class);

    @Mock
    Calculator mockedCalculator;

    @InjectMocks
    Calculator injectMockedCalculator;

    @Spy
    Calculator spyMockedCalculator;

    @Test
    void test4() {

        Calculator mockedCalculator = mock(Calculator.class);
        when(mockedCalculator.division(0, 4)).thenReturn(0);
        when(mockedCalculator.division(0, 4)).thenThrow(IllegalArgumentException.class);
        doThrow(IllegalArgumentException.class).when(mockedCalculator).division(0, 4);
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

        when(mockedList.get(anyInt())).thenReturn(999);
        when(mockedList.get(0)).thenReturn(2);
        doReturn(3).when(mockedList).get(1);
        doReturn(4).when(mockedList).get(2);
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

        //Calculator calculator = new Calculator();
        Calculator calculator = mock(Calculator.class);

        when(calculator.add(1, 2)).thenReturn(3);
        when(calculator.add(10, 2)).thenReturn(12);

        int add = calculator.add(10, 2);
        System.out.println("add = " + add);

        verify(calculator).add(10, 2);
    }
}
