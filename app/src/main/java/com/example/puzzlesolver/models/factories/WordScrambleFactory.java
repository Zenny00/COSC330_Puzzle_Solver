package com.example.puzzlesolver.models.factories;

import com.example.puzzlesolver.models.Puzzle;
import com.example.puzzlesolver.models.WordScramblePuzzle;

public class WordScrambleFactory implements PuzzleFactory {
    @Override
    public Puzzle createPuzzle(String input) {
        Puzzle puzzle = null;

        puzzle = new WordScramblePuzzle();

        return puzzle;
    }

    //Use above to determine between scramble and spaces
    @Override
    public int findType(String input) {
        return 0;
    }
}
