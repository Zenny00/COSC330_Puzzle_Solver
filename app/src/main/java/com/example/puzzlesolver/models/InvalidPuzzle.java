package com.example.puzzlesolver.models;

import android.util.Log;

public class InvalidPuzzle implements Puzzle {
    @Override
    public boolean isSolvable() {
        return false; //This puzzle has no solution
    }

    @Override
    public void setProblem(String input) { }

    @Override
    public String findSolution() {
        return null;
    }
}
