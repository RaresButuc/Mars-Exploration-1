package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.calculators.service.CoordinateCalculator;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.service.MapConfigurationValidator;
import com.codecool.marsexploration.mapelements.model.Map;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.placer.MapElementPlacer;

public class MapGeneratorImpl implements MapGenerator {
    private final MapElementPlacer mapElementPlacer;
    private final MapElementsGenerator mapElementsGenerator;
    private final CoordinateCalculator coordinateCalculator;
    private final MapConfigurationValidator mapConfigurationValidator;

    public MapGeneratorImpl(MapElementPlacer mapElementPlacer, MapElementsGenerator mapElementsGenerator, CoordinateCalculator coordinateCalculator, MapConfigurationValidator mapConfigurationValidator) {
        this.mapElementPlacer = mapElementPlacer;
        this.mapElementsGenerator = mapElementsGenerator;
        this.coordinateCalculator = coordinateCalculator;
        this.mapConfigurationValidator = mapConfigurationValidator;
    }

    @Override
    public Map generate(MapConfiguration mapConfig) {

        if(mapConfigurationValidator.validate(mapConfig)){
            System.out.println("Map config is valid");
            int number = (int) Math.sqrt(mapConfig.mapSize()) + 2;
            String[][] mapRepresentation = new String[number][number];
            for (int i = 0; i < number; i++) {
                for (int j = 0; j < number; j++) {
                    mapRepresentation[i][j] = " ";
                }
            }

            Iterable<MapElement> allElements = mapElementsGenerator.createAll(mapConfig);
            int i;
            int limit = 1000;
            for (MapElement mapElement : allElements) {
                Coordinate randomCoordinate = coordinateCalculator.getRandomCoordinate(mapElement.getDimension());
                i = 0;
                while (!mapElementPlacer.canPlaceElement(mapElement, mapRepresentation, randomCoordinate) && i < limit) {
                    randomCoordinate = coordinateCalculator.getRandomCoordinate(number - mapElement.getDimension());
                    i++;
                }
                if (i < limit) {
                    mapRepresentation = mapElementPlacer.placeElement(mapElement, mapRepresentation, randomCoordinate);
                }
            }
            return new Map(mapRepresentation);
        } else {
            System.out.println("map config is not valid");
        }
        return null;
    }
}
