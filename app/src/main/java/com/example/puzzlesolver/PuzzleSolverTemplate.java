package com.example.puzzlesolver;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.puzzlesolver.models.Puzzle;

public abstract class PuzzleSolverTemplate extends AppCompatActivity {
    private Puzzle problem;

    //Find solution to the puzzle
    public final void solveProblem()
    {
        //Read problem in from the view
        readProblem();

        //If the puzzle has a solution display it, otherwise output an error
        if (notSolvable())
            outputError();
        else
            outputSolution();
    }

    //Abstract functions to be implemented in subclasses
    public abstract void readProblem();
    public abstract void outputError();
    public abstract void outputSolution();

    //Check if the puzzle has a solution
    public boolean notSolvable()
    {
        return !problem.isSolvable();
    }
}
