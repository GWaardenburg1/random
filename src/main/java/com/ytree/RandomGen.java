package com.ytree;

import java.util.Random;

public class RandomGen {
    // Values that may be returned by nextNum()
    private int[] randomNums;
    // Probability of the occurence of randomNums
    private float[] probabilities;

    private final Random random;

    public RandomGen(int[] randomNums, float[] probablities) {
        this.randomNums = randomNums;
        this.probabilities = probablities;
        random = new Random();
        validateSeedData();
    }

    /**
     * Returns one of the randomNums. When this method is called
     * multiple times over a long period, it should return the
     * numbers roughly with the initialized probabilities.
     */
    public int nextNum() {
        float randomValue = random.nextFloat();
        float cumulativeProbability = 0.00f;
        for (int i = 0; i < probabilities.length; i++) {
            cumulativeProbability += probabilities[i];
            if (randomValue <= cumulativeProbability) {
                return randomNums[i];
            }
        }
        // this will only happen in the probablities are not correctly defined
        throw new IllegalStateException("Unable to generate a random number.");
    }

    private void validateSeedData() {
        validateListLength();
        validateProbabilitesTotal();
    }

    private void validateListLength(){
        if (randomNums.length != probabilities.length) {
            throw new IllegalStateException(
                    String.format(
                            "Unable to generate a random number. The list of numbers provided is lenghth: %d and the list of probablities is length: %d",
                            randomNums.length, probabilities.length));
        }
    }

    private void validateProbabilitesTotal(){
        float probablitiesTotal = 0.0f;
        for(int i = 0; i < probabilities.length; i++){
            probablitiesTotal += probabilities[i];
        }
        if(probablitiesTotal != 1){
            throw new IllegalStateException("Unable to generate a random number. The list of probablities provided does not sum to 1");
        }
    }
}
