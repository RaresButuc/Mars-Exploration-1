package com.codecool.marsexploration.output.service;

import com.codecool.marsexploration.mapelements.model.Map;

import java.io.*;

public class MapFileWriteImpl implements MapFileWriter {


    @Override
    public void writeMapFile(Map map, String file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.println(map);

            printWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
