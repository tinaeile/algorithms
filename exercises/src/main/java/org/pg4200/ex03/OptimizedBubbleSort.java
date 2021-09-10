package org.pg4200.ex03;

import java.util.Comparator;

public class OptimizedBubbleSort {

    public <T> int sort(T[] array, Comparator<T> comparator, boolean optimized) {

        if (array == null) {
            return 0;
        }

        // Number of times comparator#compare is called
        int counter = 0;

        // Was anything swapped this iteration
        boolean swapped = true;
        int lastSwap = array.length - 1;

        while (swapped) {

            swapped = false;

            int limit = array.length - 1;

            if (optimized) {
                limit = lastSwap;
            }

            for (int i = 0; i < limit; i++) {
                counter++;

                int j = i + 1;

                if (comparator.compare(array[i], array[j]) > 0) {
                    T tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;

                    swapped = true;
                    lastSwap = i;
                }
            }
        }


        return counter;
    }
}
