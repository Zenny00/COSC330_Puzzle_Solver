package com.example.puzzlesolver.models;

public class ShapePuzzle extends ConverterPuzzle {
    //private String accepted[] = {"200", "201", "202", "203", "204", "205", "206", "207", "208"}; //ID 3
    //private String accepted_shape_values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String shape_values[];

    public ShapePuzzle()
    {
        super(new String[]{"200", "201", "202", "203", "204", "205", "206", "207", "208"}, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
    }
}
