package com.codecool.marsexploration.calculators.service;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DimensionCalculatorImplTest {
    DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();

    @Test
    public void calculateRealDimension() {
        int dimensionCalculated = dimensionCalculator.calculateDimension(20, 3);

        assertEquals(8, dimensionCalculated);

    }
}
