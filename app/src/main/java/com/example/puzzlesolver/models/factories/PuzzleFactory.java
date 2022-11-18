package com.example.puzzlesolver.models.factories;

import com.example.puzzlesolver.models.Puzzle;

public interface PuzzleFactory {
    public Puzzle createPuzzle(String input);
    public int findType(String input);
}
