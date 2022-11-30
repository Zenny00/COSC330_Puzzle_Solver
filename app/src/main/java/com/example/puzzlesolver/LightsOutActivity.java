package com.example.puzzlesolver;

import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.puzzlesolver.models.LightsOutPuzzle;
import com.google.android.material.textfield.TextInputEditText;

public class LightsOutActivity extends PuzzleSolverTemplate{
    private final int MAX_ROWS = 6;
    private final int MAX_COLS = 6;

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
        String rows = rowInput.getText().toString();
        String cols = colInput.getText().toString();
        int numRows = MAX_ROWS + 1, numCols = MAX_COLS + 1;

        if (!(rows.contentEquals("") || cols.contentEquals(""))) {
            numRows = Integer.parseInt(rowInput.getText().toString());
            numCols = Integer.parseInt(colInput.getText().toString());
        }
        String puzzle = String.valueOf(numRows) + numCols;

        final boolean OUT_OF_BOUNDS_RIGHT = numRows > MAX_ROWS || numCols > MAX_COLS;
        final boolean OUT_OF_BOUNDS_LEFT = numRows < 0 || numCols < 0;
        if (!(OUT_OF_BOUNDS_LEFT || OUT_OF_BOUNDS_RIGHT)) {
            for (int i = 0; i < numRows; i++) {
                TableRow row = (TableRow) lightGrid.getChildAt(i);
                for (int j = 0; j < numCols; j++) {
                    ImageButton btn = (ImageButton) row.getChildAt(j);
                    if (btn.getContentDescription().equals(getString(R.string.btn_state_on)))
                        puzzle += "1";
                    else
                        puzzle += "0";
                }
            }
        }
        problem.setProblem(puzzle);
    }
    @Override
    public void outputError() {
        // TODO: Make this better with animations
        outputTV.setText(R.string.index_err_msg);
    }
    @Override
    public void outputSolution() {
        outputTV.setText(problem.findSolution());
    }

    // BUTTON FUNCTIONS
    public void gotoHome(View view) {
        finish();
    }
    public void callSolver(View view) {solveProblem();}
    public void toggleBtn(View view) {
        if (((ImageButton) view).getContentDescription().toString().contentEquals(getString(R.string.btn_state_on))) {
            ((ImageButton) view).setContentDescription(getString(R.string.btn_state_off));
            ((ImageButton) view).setImageResource(R.drawable.lights_off);
        } else {
            ((ImageButton) view).setContentDescription(getString(R.string.btn_state_on));
            ((ImageButton) view).setImageResource(R.drawable.lights_on);
        }
    }
    public void setDisabled(View view) {
        String column = colInput.getText().toString();
        String row = rowInput.getText().toString();

        int numCols = 6, numRows = 6;
        if (!column.contentEquals("")
            && Integer.parseInt(column) > 0
            && Integer.parseInt(column) < 7)
            numCols = Integer.parseInt(column);
        if (!row.contentEquals("")
                && Integer.parseInt(row) > 0
                && Integer.parseInt(row) < 7)
            numRows = Integer.parseInt(row);

        for (int i = 0; i < MAX_ROWS; i++) {
            TableRow tableRow = (TableRow) lightGrid.getChildAt(i);
            for (int j = 0; j < MAX_COLS; j++) {
                ImageButton btn = (ImageButton) tableRow.getChildAt(j);
                if (i >= numRows || j >= numCols) {
                    btn.setClickable(false);
                    btn.setContentDescription(getString(R.string.btn_state_disabled));
                    btn.setImageResource(R.drawable.lights_disabled);
                } else if (btn.getContentDescription().equals(getString(R.string.btn_state_disabled))) {
                    btn.setClickable(true);
                    btn.setContentDescription(getString(R.string.btn_state_off));
                    btn.setImageResource(R.drawable.lights_off);
                }
            }
        }

    }

    // HELPER FUNCTIONS
    public void initialize() {
        problem = new LightsOutPuzzle();
        outputTV = findViewById(R.id.lights_out_output);
        rowInput = findViewById(R.id.row_input);
        colInput = findViewById(R.id.col_input);
        lightGrid = findViewById(R.id.lightGrid);

        // sets all the lights to disabled so that user must specify dimensions
        // this isn't a great way to do it but it's the way that works
        for (int i = 0; i < MAX_ROWS; i++) {
            TableRow tableRow = (TableRow) lightGrid.getChildAt(i);
            for (int j = 0; j < MAX_COLS; j++) {
                ImageButton btn = (ImageButton) tableRow.getChildAt(j);
                btn.setClickable(false);
                btn.setContentDescription(getString(R.string.btn_state_disabled));
                btn.setImageResource(R.drawable.lights_disabled);

            }
        }
    }
}
