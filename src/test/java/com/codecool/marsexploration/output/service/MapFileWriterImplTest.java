package com.codecool.marsexploration.output.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.File;

import static org.junit.Assert.assertTrue;

public class MapFileWriterImplTest {

    @ParameterizedTest
    @CsvSource({
            "src/main/resources/exploration-3.map",
            "src/main/resources/exploration-4.map",
            "src/main/resources/exploration-5.map"
    })
    public void writeMapFileTest(String filePath){
            File file = new File(filePath);
            assertTrue(file.exists() && !file.isDirectory());
    }
}
