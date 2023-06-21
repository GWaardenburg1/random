package com.ytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

/**
 * Unit test for RandomGen.
 */
public class RandomNumberGenetatorTest {
    @Test
    public void testNextNum() {
        int[] randomNums = { -1, 0, 1, 2, 3 };
        float[] probabilities = { 0.01f, 0.3f, 0.58f, 0.1f, 0.01f };
        RandomGen generator = new RandomGen(randomNums, probabilities);

        int[] counts = new int[5];
        int iterations = 100;

        for (int i = 0; i < iterations; i++) {
            int num = generator.nextNum();
            counts[num + 1]++; // Shift index by 1 to match array indices as we are using test data with -1
        }

        // Expected probabilities
        float[] expectedProbabilities = { 0.01f, 0.3f, 0.58f, 0.1f, 0.01f };

        for (int i = 0; i < counts.length; i++) {
            float probability = (float) counts[i] / iterations;
            // Allow 10% error tolerance as the sample size is small.
            assertEquals(expectedProbabilities[i], probability, 0.10); 
        }
    }

    @Test
    public void testNextNumAccurate() {
        int[] randomNums = { -1, 0, 1, 2, 3 };
        float[] probabilities = { 0.01f, 0.3f, 0.58f, 0.1f, 0.01f };
        RandomGen generator = new RandomGen(randomNums, probabilities);

        int[] counts = new int[5];
        int iterations = 1000000;

        for (int i = 0; i < iterations; i++) {
            int num = generator.nextNum();
            counts[num + 1]++; // Shift index by 1 to match array indices as we are using test data with -1
        }

        // Expected probabilities
        float[] expectedProbabilities = { 0.01f, 0.3f, 0.58f, 0.1f, 0.01f };

        for (int i = 0; i < counts.length; i++) {
            float probability = (float) counts[i] / iterations;
            // Allow 0.1% error tolerance as the sample is huge.
            assertEquals(expectedProbabilities[i], probability, 0.001); 
        }
    }

    @Test
    public void testNextNumWithInvalidProbabilitiesList() {
        int[] randomNums = { -1, 0, 1, 2, 3 };
        float[] probabilities = { 0.01f, 0.3f };
        RandomGen generator = new RandomGen(randomNums, probabilities);

        assertThrows(IllegalStateException.class, generator::nextNum);
    }

    @Test
    public void testNextNumWithInvalidProbabilitiesTotal() {
        int[] randomNums = { -1, 0, 1, 2, 3 };
        float[] probabilities = { 0.01f, 0.3f, 0.58f, 0.1f, 1.01f };
        RandomGen generator = new RandomGen(randomNums, probabilities);

        assertThrows(IllegalStateException.class, generator::nextNum);
    }

}
