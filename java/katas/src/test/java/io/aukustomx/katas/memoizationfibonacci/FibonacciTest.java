package io.aukustomx.katas.memoizationfibonacci;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {

    private final int number = 30;
    @Test
    void fibonacciWithMemoization() {
        int fibonacci = Fibonacci.fibonacciWithMemoization(number);
        System.out.println("Fibonacci: " + fibonacci);
    }

    @Test
    void fibonacciWithoutMemoization() {
        int fibonacci = Fibonacci.fibonacciWithoutMemoization(number);
        System.out.println("Fibonacci: " + fibonacci);
    }
}