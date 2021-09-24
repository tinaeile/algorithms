package org.pg4200.ex04;

public class FibonacciImpl implements Fibonacci {
    @Override
    public int compute(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n is invalid negative value: " + n);
        }

        if (n == 0 || n == 1) {
            return n;
        }

        return compute(n - 2) + compute(n - 1);
    }
}
