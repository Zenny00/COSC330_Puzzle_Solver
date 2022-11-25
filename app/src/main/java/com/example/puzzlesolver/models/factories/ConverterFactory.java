package com.example.puzzlesolver.models.factories;

import com.example.puzzlesolver.models.Puzzle;

public class ConverterFactory implements PuzzleFactory {

    //Constants to hold the accepted icons for each puzzle
    private final String accepted_binary[] = {"5", "6"};
    private final String accepted_morse[] = {"18", "19"};
    private final String accepted_roman[] = {"20", "21", "22", "23", "24", "25", "26", "27", "28"};
    private final String accepted_shapes[] = {"15", "16", "17"};
    private final String accepted_arrow_line[] = {"1", "2", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};

    @Override
    public Puzzle createPuzzle(String input) {
        return null;
    }

    @Override
    public int findType(String input) {
        return 0;
    }
}
