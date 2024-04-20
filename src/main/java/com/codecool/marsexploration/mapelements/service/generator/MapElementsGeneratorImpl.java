package com.codecool.marsexploration.mapelements.service.generator;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;
import com.codecool.marsexploration.mapelements.model.MapElement;
import com.codecool.marsexploration.mapelements.service.builder.MapElementBuilder;

import java.util.ArrayList;
import java.util.List;

public class MapElementsGeneratorImpl implements MapElementsGenerator {
    private final MapElementBuilder mapElementBuilder;

    public MapElementsGeneratorImpl(MapElementBuilder mapElementBuilder) {
        this.mapElementBuilder = mapElementBuilder;
    }

    @Override
    public Iterable<MapElement> createAll(MapConfiguration mapConfig) {
        List<MapElement> allElements = new ArrayList<>();
        for (MapElementConfiguration mapElementConfiguration : mapConfig.mapElementConfigurations()) {
            for (ElementToSize elementToSize : mapElementConfiguration.elementToSizes()) {
                for (int i = 0; i < elementToSize.elementCount(); i++) {
                    allElements.add(mapElementBuilder.build(elementToSize.size(), mapElementConfiguration.symbol(), mapElementConfiguration.name(), mapElementConfiguration.dimensionGrowth(), mapElementConfiguration.preferredLocationSymbol()));
                }
            }
        }
        return allElements;
    }
}
