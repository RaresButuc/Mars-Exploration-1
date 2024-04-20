package com.codecool.marsexploration.configuration.service;

import com.codecool.marsexploration.configuration.model.ElementToSize;
import com.codecool.marsexploration.configuration.model.MapConfiguration;
import com.codecool.marsexploration.configuration.model.MapElementConfiguration;

import java.util.Objects;

public class MapConfigurationValidatorImpl implements MapConfigurationValidator {
    @Override
    public boolean validate(MapConfiguration mapConfig) {
        if (!validateIfTotalElementsIsLessThanMapSize(mapConfig)) {
            return false;
        }
        for (MapElementConfiguration mapElementConfiguration : mapConfig.mapElementConfigurations()) {
            for (ElementToSize elementToSize : mapElementConfiguration.elementToSizes()) {
                switch (mapElementConfiguration.symbol()) {
                    case "#" -> {
                        if (!mapElementConfiguration.name().equals("mountain") || elementToSize.size() < 2 || mapElementConfiguration.dimensionGrowth() != 3 || !Objects.equals(mapElementConfiguration.preferredLocationSymbol(), "")) {
                            return false;
                        }
                    }
                    case "&" -> {
                        if (!mapElementConfiguration.name().equals("pit") || elementToSize.size() < 2 || mapElementConfiguration.dimensionGrowth() != 10 || !Objects.equals(mapElementConfiguration.preferredLocationSymbol(), "")) {
                            return false;
                        }
                    }
                    case "%" -> {
                        if (!mapElementConfiguration.name().equals("mineral") || elementToSize.size() > 1 || mapElementConfiguration.dimensionGrowth() != 0 || !Objects.equals(mapElementConfiguration.preferredLocationSymbol(), "#")) {
                            return false;
                        }
                    }
                    case "*" -> {
                        if (!mapElementConfiguration.name().equals("water") || elementToSize.size() > 1|| mapElementConfiguration.dimensionGrowth() != 0 || !Objects.equals(mapElementConfiguration.preferredLocationSymbol(), "&")) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
@Override
    public boolean validateIfTotalElementsIsLessThanMapSize(MapConfiguration mapConfiguration) {
        int totalSize = 0;
        for (MapElementConfiguration mapElementConfiguration : mapConfiguration.mapElementConfigurations()) {
            for (ElementToSize elementToSize : mapElementConfiguration.elementToSizes()) {
                totalSize += elementToSize.size();
            }
        }
        return totalSize <= mapConfiguration.mapSize() * mapConfiguration.elementToSpaceRatio();
    }
}
