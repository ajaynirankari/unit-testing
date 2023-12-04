package com.gl;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MockitoTest {

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
