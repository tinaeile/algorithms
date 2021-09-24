package org.pg4200.ex04;

public class FibonacciImplMemoized implements Fibonacci {

    private int[] x = new int[100];

    @Override
    public int compute(int n) {

        if (n < 0) {
            throw new IllegalArgumentException("n is invalid negative value: " + n);
        }

        if (n == 0 || n == 1) {
            return n;
        }

        if (n < x.length && x[n] != 0) {
            return x[n];
        }

        int result = compute(n - 2) + compute(n - 1);

        if (n < x.length) {
            x[n] = result;
        }

        return result;
    }
}