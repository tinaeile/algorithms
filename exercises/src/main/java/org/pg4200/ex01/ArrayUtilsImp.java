package org.pg4200.ex01;

public class ArrayUtilsImp implements ArrayUtils {
    @Override
    public int min(int[] array) throws IllegalArgumentException {

        checkArray(array);
        int min = array[0];

        for (int current : array) {
            if (current < min) {
                min = current;
            }
        }

        return min;
    }

    @Override
    public int max(int[] array) throws IllegalArgumentException {
        checkArray(array);
        int max = array[0];

        for (int current : array) {
            if (current > max) {
                max = current;
            }
        }

        return max;
    }

    @Override
    public double mean(int[] array) throws IllegalArgumentException {
        checkArray(array);
        double sum = array[0];

        for (int i = 1; i < array.length; i++) {
            sum += array[i];
        }

        return sum / array.length;
    }

    private void checkArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Invalid empty array");
        }
    }
}
