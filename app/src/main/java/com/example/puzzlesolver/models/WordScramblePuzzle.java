package com.example.puzzlesolver.models;

import android.widget.TextView;

import com.example.puzzlesolver.ParseWords;

public class WordScramblePuzzle implements Puzzle {

    private ParseWords ps;

    @Override
    public boolean isSolvable() {
        if(ps.getSolved())
        {
            return true;
        }
        return false;
    }

    @Override
    public void setProblem(String input) {

    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        solution = ps.getPrinter();

        return solution;
    }
}
