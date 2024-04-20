package com.codecool.marsexploration.mapelements.service.builder;

import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.mapelements.model.MapElement;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.of;

public class MapElementBuilderTest {


    @ParameterizedTest
    @CsvSource({
            "20, #, mountain, 3, '' ",
            "20, #, mountain, 3, '' ",
            "30, #, mountain, 3, '' ",
            "10, &, pit, 10, ''",
            "10, &, pit, 10, ''",
            "20, &, pit, 10, ''",
            "1, %, mineral, 0, '#'",
            "1, *, water, 0, '&'"
    })
    public void mapElementBuildTest(int size, String symbol, String name, int dimensionGrowth, String preferredLocationSymbol){

        MapElementBuilderImpl builder = new MapElementBuilderImpl();
        MapElement mapElement = builder.build(size, symbol, name,  dimensionGrowth, preferredLocationSymbol);
        System.out.println(mapElement);
        assertNotNull(mapElement);
    }
}
