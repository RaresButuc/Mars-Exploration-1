package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilderImpl;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MapElementsGeneratorImplTest {
    MapElementConfiguration mountainsCfg = new MapElementConfiguration(
            "#",
            "mountain",
            List.of(
                    new ElementToSize(2, 20),
                    new ElementToSize(1, 30)
            ),
            3,
            ""
    );

    MapElementConfiguration pitCfg = new MapElementConfiguration(
            "&",
            "pit",
            List.of(
                    new ElementToSize(2, 10),
                    new ElementToSize(1, 20)
            ),
            10,
            ""
    );

    MapElementConfiguration mineralCfg = new MapElementConfiguration(
            "%",
            "mineral",
            List.of(
                    new ElementToSize(10, 1)
            ),
            0,
            "#"
    );

    MapElementConfiguration waterCfg = new MapElementConfiguration(
            "*",
            "water",
            List.of(
                    new ElementToSize(10, 1)
            ),
            0,
            "&"
    );

    List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg, pitCfg, mineralCfg, waterCfg);
    MapConfiguration mapConfig = new MapConfiguration(1000, 0.5,elementsCfg);
    @Test
    public void createAllTest(){
        MapElementBuilder builder = new MapElementBuilderImpl();
        MapElementsGenerator mapElementsGenerator = new MapElementsGeneratorImpl(builder);
        Iterable<MapElement> allElements = mapElementsGenerator.createAll(mapConfig);
        assertNotNull(allElements);
        for(MapElement mapElement : allElements){
            double expected = Math.pow(mapElement.getRepresentation().length, 2);
            double actual = Math.pow(mapElement.getDimension(), 2);
            assertEquals(actual, expected, 0.0001); // Use the correct assertEquals method with a delta value of 0.0001 (or any other suitable value)
        }
    }
}
