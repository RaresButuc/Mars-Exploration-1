package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapConfigurationValidatorImplTest {
    MapConfiguration mapConfiguration;
    MapConfigurationValidator mapValidator = new MapConfigurationValidatorImpl();

    public MapConfiguration spaceRationTooBig() {
        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                "#",
                "mountain",
                List.of(
                        new ElementToSize(2, 501)
                ),
                3,
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }

    public MapConfiguration wrongName() {
        MapElementConfiguration pitCfg = new MapElementConfiguration(
                "&",
                "mountain",
                List.of(
                        new ElementToSize(2, 25),
                        new ElementToSize(1, 50)
                ),
                10,
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(pitCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }

    public MapConfiguration wrongDimension() {
        MapElementConfiguration mineralCfg = new MapElementConfiguration(
                "%",
                "mineral",
                List.of(
                        new ElementToSize(2, 2),
                        new ElementToSize(1, 3)
                ),
                0,
                "#"
        );

        List<MapElementConfiguration> elementsCfg = List.of(mineralCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }

    public MapConfiguration wrongDimensionGrowth() {
        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                "#",
                "mountain",
                List.of(
                        new ElementToSize(2, 20),
                        new ElementToSize(1, 30)
                ),
                30,
                ""
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }

    public MapConfiguration wrongPreferredLocation() {
        MapElementConfiguration mountainsCfg = new MapElementConfiguration(
                "*",
                "water",
                List.of(
                        new ElementToSize(20, 1),
                        new ElementToSize(10, 1)
                ),
                0,
                "#"
        );

        List<MapElementConfiguration> elementsCfg = List.of(mountainsCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }

    public MapConfiguration correctSizeFromValidateSize(){
        MapElementConfiguration mineralCfg = new MapElementConfiguration(
                "%",
                "mineral",
                List.of(
                        new ElementToSize(10, 1)
                ),
                0,
                "#"
        );
        List<MapElementConfiguration> elementsCfg = List.of(mineralCfg);
        return new MapConfiguration(1000, 0.5, elementsCfg);
    }
    @Test
    public void testAllAspects() {
        assertFalse(mapValidator.validateIfTotalElementsIsLessThanMapSize(spaceRationTooBig())); //Aspect Ratio
        System.out.println("Aspect Ration is Wrong from Validate total size");
        assertFalse(mapValidator.validate(wrongName())); //Wrong Name
        System.out.println("Name is Wrong");
        assertFalse(mapValidator.validate(wrongDimension())); //Wrong Dimensional Size
        System.out.println("Dimensional Size is Wrong");
        assertFalse(mapValidator.validate(wrongDimensionGrowth())); //Wrong Dimension Growth
        System.out.println("Dimension Growth is Wrong");
        assertFalse(mapValidator.validate(wrongPreferredLocation())); //Wrong Preferred Location
        System.out.println("Preferred Location is Wrong");
        assertTrue(mapValidator.validate(correctSizeFromValidateSize()));
        System.out.println("Correct size");
        assertFalse(mapValidator.validate(spaceRationTooBig())); //Aspect Ratio
        System.out.println("Aspect Ration is Wrong from Valid method");
    }
}
