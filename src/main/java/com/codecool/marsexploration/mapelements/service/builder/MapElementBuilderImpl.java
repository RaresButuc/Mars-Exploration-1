package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;

public class MapElementBuilderImpl implements MapElementBuilder {

    @Override
    public MapElement build(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol) {
        DimensionCalculatorImpl dimensionCalculator = new DimensionCalculatorImpl();
        CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(dimensionCalculator);

        int dimension = dimensionCalculator.calculateDimension(size, dimensionGrowth);
        String[][] representation = new String[dimension][dimension];
        for (int i = 0; i < size; i++) {
            Coordinate coordinate = coordinateCalculator.getRandomCoordinate(dimension);
            if (representation[coordinate.x()][coordinate.y()] == null) {
                representation[coordinate.x()][coordinate.y()] = symbol;
            } else {
                i--;
            }
        }

        //todo replace null with string from the beginning
        for (int i = 0; i < representation.length; i++) {
            for (int j = 0; j < representation[i].length; j++) {
                if (representation[i][j] == null) {
                    representation[i][j] = " ";
                }
            }
        }
        return new MapElement(representation, name, dimension, preferredLocationSymbol);
    }
}
