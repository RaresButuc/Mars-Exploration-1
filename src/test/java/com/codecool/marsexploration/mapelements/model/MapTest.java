package com.codecool.marsexploration.mapelements.model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {

    @Test
    public void testCreateStringRepresentation(){
        String[][]arr ={
                {"#", "#", "#"},
                {"%", "%", "%"},
                {"*", "*", "*"},
                {"&", "&", "&"}
        };
        String expected = "###\r\n%%%\r\n***\r\n&&&\r\n";
        Map map = new Map(arr);
        String result = map.toString();
        System.out.println("Expected: " + expected + " Result: " + result);
        assertEquals(expected, result);
    }
}
