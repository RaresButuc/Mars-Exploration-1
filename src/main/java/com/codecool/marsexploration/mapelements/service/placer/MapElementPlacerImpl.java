package com.codecool.marsexploration.mapelements.service.placer;

import com.codecool.marsexploration.calculators.model.Coordinate;
import com.codecool.marsexploration.mapelements.model.MapElement;

import java.util.Objects;

public class MapElementPlacerImpl implements MapElementPlacer {
    @Override
    public boolean canPlaceElement(MapElement element, String[][] map, Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();

        if (x - 1 < 0 || x >= map.length || y - 1 < 0 || y >= map[0].length) {
            return false;
        }
        switch (element.getName()) {
            case "mountain" -> {
                return checkMountainsAndPits(element, map, coordinate, element.getDimension(), "#");
            }
            case "pit" -> {
                return checkMountainsAndPits(element, map, coordinate, element.getDimension(), "&");
            }
            case "mineral" -> {
                return checkWaterAndMineral(map, coordinate, "#");
            }
            case "water" -> {
                return checkWaterAndMineral(map, coordinate, "&");
            }
        }
        return false;
    }

    @Override
    public String[][] placeElement(MapElement element, String[][] map, Coordinate coordinate) {
        int x = coordinate.x();
        int y = coordinate.y();
        if (x < 0 || x + element.getRepresentation().length > map.length ||
                y < 0 || y + element.getRepresentation()[0].length > map[0].length) {
            return map;
        }
        switch (element.getName()) {
            case "mountain" -> {
                placeMountainOrPit(element, map, coordinate, "#");
            }
            case "pit" -> {
                placeMountainOrPit(element, map, coordinate, "&");
            }
            case "mineral" -> {
                map[x][y] = "%";
            }
            case "water" -> {
                map[x][y] = "*";
            }
        }
        return map;
    }

    private boolean checkWaterAndMineral(String[][] map, Coordinate coordinate, String string) {
        int x = coordinate.x();
        int y = coordinate.y();
        return Objects.equals(map[x][y], " ") &&
                (Objects.equals(map[x][y - 1], string) ||
                        Objects.equals(map[x][y + 1], string) ||
                        Objects.equals(map[x - 1][y], string) ||
                        Objects.equals(map[x + 1][y], string) ||
                        Objects.equals(map[x + 1][y + 1], string) ||
                        Objects.equals(map[x + 1][y - 1], string) ||
                        Objects.equals(map[x - 1][y + 1], string) ||
                        Objects.equals(map[x - 1][y - 1], string));
    }

    private boolean checkMountainsAndPits(MapElement element, String[][] map, Coordinate coordinate, int size, String string) {
        int x = coordinate.x();
        int y = coordinate.y();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (element.getRepresentation()[i][j].equals(string) && !(map[i + x][j + y].equals(" "))) {
                    return false;
                }
            }
        }
        return true;
    }

    private void placeMountainOrPit(MapElement element, String[][] map, Coordinate coordinate, String string) {
        int x = coordinate.x();
        int y = coordinate.y();
        for (int i = 0; i < element.getRepresentation()[0].length; i++) {
            for (int j = 0; j < element.getRepresentation()[0].length; j++) {
                if (Objects.equals(element.getRepresentation()[i][j], string)) {
                    map[i + x][j + y] = string;
                }
            }
        }
    }
}
