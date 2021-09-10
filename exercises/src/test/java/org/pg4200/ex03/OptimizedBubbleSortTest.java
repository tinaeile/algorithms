package org.pg4200.ex03;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OptimizedBubbleSortTest {

    private OptimizedBubbleSort sorter = new OptimizedBubbleSort();

    private class StringComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    @Test
    public void testNull() {

        int comparisons = sorter.sort(null, new StringComparator(), false);

        assertEquals(0, comparisons);
    }

    @Test
    public void testAlreadySorted() {

        String[] array = {"a", "b", "c", "d"};

        int comparisons = sorter.sort(array, new StringComparator(), false);

        assertEquals(3, comparisons);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testInverted() {

        String[] array = {"d", "c", "b", "a"};

        int comparisons = sorter.sort(array, new StringComparator(), false);

        assertEquals(12, comparisons);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testBase() {

        String[] array = {"d", "a", "c", "b"};

        int comparisons = sorter.sort(array, new StringComparator(), false);

        assertEquals(9, comparisons);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
    }


    @Test
    public void testNearly() {

        String[] array = {"c", "b", "a", "d", "e", "f"};
        int optimized = sorter.sort(array, new StringComparator(), true);

        array = new String[]{"c", "b", "a", "d", "e", "f"};
        int base = sorter.sort(array, new StringComparator(), false);

        assertTrue(optimized < base);
        assertTrue(optimized < base / 2);

        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
        assertEquals("d", array[3]);
        assertEquals("e", array[4]);
        assertEquals("f", array[5]);
    }
}