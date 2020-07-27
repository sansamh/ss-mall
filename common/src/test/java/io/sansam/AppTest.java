package io.sansam;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    public static void main(String[] args) {

        int cap = 17;
        int i = Integer.numberOfLeadingZeros(cap - 1);
        System.out.println(i);
        int n = -1 >>> i;
        System.out.println(n);
        int res = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;

        System.out.println(res);


    }
}
