package org.pg4200.ex01;

import org.junit.jupiter.api.Test;
import org.pg4200.ex01.TriangleClassification.Classification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.pg4200.ex01.TriangleClassification.Classification.*;
import static org.pg4200.ex01.TriangleClassification.classify;

public class TriangleClassificationTest {
    @Test
    public void testNotATriangleNegValues() {
        Classification res = classify(-2, 59, 59);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testIfEquilateral() {
        Classification res = classify(60, 60, 60);
        assertEquals(EQUILATERAL, res);
    }

    @Test
    public void testIfIsosceles() {
        Classification res = classify(40, 30, 30);
        assertEquals(ISOSCELES, res);
    }

    @Test
    public void testIfTooLong() {
        Classification res = classify(2000, 10, 10);
        assertEquals(NOT_A_TRIANGLE, res);
    }

    @Test
    public void testIfScalene() {
        Classification res = classify(35, 70, 100);
        assertEquals(SCALENE, res);
    }
}
