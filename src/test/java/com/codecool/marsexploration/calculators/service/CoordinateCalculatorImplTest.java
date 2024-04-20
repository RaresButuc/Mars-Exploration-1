package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;
import org.junit.jupiter.api.Assertions;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.List;

//import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoordinateCalculatorImplTest {
    private final DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
    CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(dimensionCalculator);

    List<Coordinate> allCoordinates = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(new Coordinate(2, 2), 2);

    @Test
    public void getRandomCoordinatesTest() {
        Coordinate coordinate = coordinateCalculator.getRandomCoordinate(20);
        boolean getCords = coordinate.x() <= 20 && coordinate.y() <= 20;
        Assertions.assertTrue(getCords);
    }

    @Test
    public void getAdjacentCoordinatesFirst() {
        List<Coordinate> expectedCoordinates = List.of(new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(3, 2), new Coordinate(3, 3));
        System.out.println("Expected: "+ expectedCoordinates + " Actual: "+ allCoordinates);
        assertEquals(expectedCoordinates, allCoordinates);
    }

    @Test
    public void getAdjacentCoordinatesSecond() {
        List<Coordinate> actualCoordinates = (List<Coordinate>) coordinateCalculator.getAdjacentCoordinates(allCoordinates, 2);
        List<Coordinate> expectedCoordinates = List.of(new Coordinate(4, 4), new Coordinate(1, 1), new Coordinate(4, 1), new Coordinate(1, 2), new Coordinate(4, 2), new Coordinate(1, 3), new Coordinate(4, 3), new Coordinate(1, 4), new Coordinate(2, 1), new Coordinate(2, 4), new Coordinate(3, 1), new Coordinate(3, 4));

        assertTrue(actualCoordinates.size() == expectedCoordinates.size() && actualCoordinates.containsAll(expectedCoordinates) && expectedCoordinates.containsAll(actualCoordinates));
    }
}
