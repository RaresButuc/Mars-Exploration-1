package com.codecool.marsexploration.calculators.service;

import com.codecool.marsexploration.calculators.model.Coordinate;

import java.util.*;

public class CoordinateCalculatorImpl implements CoordinateCalculator {
    private final DimensionCalculator dimensionCalculator;

    public CoordinateCalculatorImpl(DimensionCalculator dimensionCalculator) {
        this.dimensionCalculator = dimensionCalculator;
    }

    @Override
    public Coordinate getRandomCoordinate(int dimension) {
        Random random = new Random();
        return new Coordinate(random.nextInt(dimension), random.nextInt(dimension));
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Coordinate coordinate, int dimension) {
        List<Coordinate> allCoordinates = new ArrayList<>();
        for (int i = coordinate.x(); i < dimension + coordinate.x(); i++) {
            for (int j = coordinate.y(); j < dimension + coordinate.y(); j++) {
                allCoordinates.add(new Coordinate(i, j));
            }
        }
        return allCoordinates;
    }

    @Override
    public Iterable<Coordinate> getAdjacentCoordinates(Iterable<Coordinate> coordinates, int dimension) {
        List<Coordinate> frame = new ArrayList<>();
        Iterator<Coordinate> itr = coordinates.iterator();
        Coordinate upLeft =  itr.next();
        Coordinate downRight = null;
        while (itr.hasNext()) {
            downRight = itr.next();
        }
        assert downRight != null;
        frame.add(new Coordinate(downRight.x() + 1, downRight.y() + 1));
        for (int i = upLeft.x() - 1; i < downRight.x() + 1; i++) {
            if(i<0) break;
            frame.add(new Coordinate(upLeft.x() - 1, i));
            frame.add(new Coordinate(downRight.x() + 1, i));
        }
        for (int j = upLeft.y() - 1; j < downRight.y() + 1; j++) {
            if(j<0) break;
            frame.add(new Coordinate(j, upLeft.y() - 1));
            frame.add(new Coordinate(j, downRight.y() + 1));
        }
        System.out.println("frame " + frame.stream().distinct().toList());
        return frame.stream().distinct().toList();
    }
}
