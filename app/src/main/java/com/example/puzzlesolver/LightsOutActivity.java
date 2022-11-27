package com.example.puzzlesolver;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.puzzlesolver.models.LightsOutPuzzle;
import com.google.android.material.textfield.TextInputEditText;

public class LightsOutActivity extends PuzzleSolverTemplate{
    // protected Puzzle problem; from super
    private TextView outputTV;
    private TextInputEditText rowInput, colInput;
    private TableLayout lightGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lights_layout);

        initialize();
    }

    // ABSTRACT FUNCTIONS FROM TEMPLATE
    @Override
    public void readProblem() {
    }
    @Override
    public void outputError() {
    }
    @Override
    public void outputSolution() {
    }

    // BUTTON FUNCTIONS
    public void gotoHome(View view) {
        finish();
    }
    public void callSolver(View view) {
        solveProblem();
    }

    // HELPER FUNCTIONS
    public void initialize() {
        problem = new LightsOutPuzzle();
        outputTV = findViewById(R.id.lights_out_output);
        rowInput = findViewById(R.id.row_input);
        colInput = findViewById(R.id.col_input);
        lightGrid = findViewById(R.id.lightGrid);
    }
}
