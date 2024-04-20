package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.calculators.service.CoordinateCalculatorImpl;
import com.codecool.marsexploration.calculators.service.DimensionCalculator;
import com.codecool.marsexploration.calculators.service.DimensionCalculatorImpl;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static javax.security.auth.login.Configuration.getConfiguration;
import static org.junit.Assert.*;

public class MapElementPlacerImplTest {
    MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();
    private final DimensionCalculator dimensionCalculator = new DimensionCalculatorImpl();
    private final CoordinateCalculator coordinateCalculator = new CoordinateCalculatorImpl(dimensionCalculator);

    //    @Test
//    void canPlaceElementTest() {
//        String[][] map = new String[33][33];
//        for (int i = 0; i < 33; i++) {
//            for (int j = 0; j < 33; j++) {
//                map[i][j] = " ";
//            }
//        }
//
//        MapElement mountain = new MapElement(new String[][]{new String[]{" "}, {"#"}, {""}}, "mountain", 2, "");
//        MapElement pit = new MapElement(new String[][]{new String[]{" "}, {"&"}, {""}}, "pit", 2, "");
//        MapElement mineral = new MapElement(new String[][]{new String[]{" "}, {"%"}, {""}}, "mineral", 1, "#");
//        MapElement water = new MapElement(new String[][]{new String[]{" "}, {"*"}, {""}}, "water", 1, "&");
//
//        assertTrue(mapElementPlacer.canPlaceElement(mountain, map, new Coordinate(2, 2)));
//        assertTrue(mapElementPlacer.canPlaceElement(pit, map, new Coordinate(2, 2)));
//
//        //When no mountain around mineral
//        assertFalse(mapElementPlacer.canPlaceElement(mineral, map, new Coordinate(2, 2)));
//        //When mountain around mineral
//        map[5][5] = "#";
//        assertTrue(mapElementPlacer.canPlaceElement(mineral, map, new Coordinate(4, 5)));
//
//        //When no pit around water
//        assertFalse(mapElementPlacer.canPlaceElement(water, map, new Coordinate(3, 2)));
//        //When pit around water
//        map[3][3] = "&";
//        assertTrue(mapElementPlacer.canPlaceElement(water, map, new Coordinate(4, 3)));
//    }
    @Test
    void canPlaceElement_returnsTrue_forEmptyMap() {
        String[][] map = createEmptyStringArray(33, 33, "empty");
        System.out.println("Harta Libera: " + map);
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl();
        MapElement mountain = mapElementBuilder.build(20, "#", "mountain", 3, "");
        System.out.println(mountain);
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();
        Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(8);

        assertTrue(mapElementPlacer.canPlaceElement(mountain, map, randomCoordinate));
    }

    @Test
    void canPlaceElement_returnsFalse_forFullMap() {
        String[][] map = createEmptyStringArray(32, 32, "full");
        System.out.println("Harta Ocupata: " + map);
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl();
        MapElement mountain = mapElementBuilder.build(20, "#", "mountain", 3, "");
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();
        Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(8);

        assertFalse(mapElementPlacer.canPlaceElement(mountain, map, randomCoordinate));
    }

    @Test
    void placeElement_placesAMountain() {
        String[][] map = createEmptyStringArray(32, 32, "empty");
        MapElementBuilder mapElementBuilder = new MapElementBuilderImpl();
        MapElement mountain = mapElementBuilder.build(20, "#", "mountain", 3, "");
        MapElementPlacer mapElementPlacer = new MapElementPlacerImpl();

        mapElementPlacer.placeElement(mountain, map, new Coordinate(4, 5));
    }


    private static MapConfiguration getConfiguration() {
        final String mountainSymbol = "#";
        final String pitSymbol = "&";
        final String mineralSymbol = "%";
        final String waterSymbol = "*";

        List<MapElementConfiguration> elementsCfg = List.of();
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }


    public static String[][] createEmptyStringArray(int rows, int columns, String type) {
        String[][] array = new String[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (type.equals("empty")) {
                    array[i][j] = " ";
                } else {
                    array[i][j] = "#";
                }
            }
        }
        return array;
    }
}
