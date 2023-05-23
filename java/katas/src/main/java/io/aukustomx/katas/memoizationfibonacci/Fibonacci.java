package io.aukustomx.katas.memoizationfibonacci;

import java.util.HashMap;
import java.util.Map;

public class Fibonacci {

    private static final Map<Integer, Integer> memo = new HashMap<>();

    public static int fibonacciWithoutMemoization(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("It's not allowed to use a number less than 0");
        }

        if (n == 0 || n == 1) {
            return n;
        }

        System.out.println("Computing fibbinacci of " + n);
        return fibonacciWithoutMemoization(n - 1) + fibonacciWithoutMemoization(n - 2);
    }

    public static int fibonacciWithMemoization(int number) {

        if (number < 0) {
            throw new IllegalArgumentException("It's not allowed to use a number less than 0");
        } else if (number == 0 || number == 1) {
            return number;
        }

        if (memo.containsKey(number)) {
            var precalculatedFibonacci = memo.get(number);
            System.out.println("Getting precalculated fibonacci of " + number + ": " + precalculatedFibonacci);
            return precalculatedFibonacci;
        }

        System.out.println("Computing fibonacci of " + number);
        var result = fibonacciWithMemoization(number - 1) + fibonacciWithMemoization(number - 2);

        memo.put(number, result);

        return result;
    }
}
