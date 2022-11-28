package com.example.puzzlesolver;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.example.puzzlesolver.models.Puzzle;
import com.example.puzzlesolver.models.factories.ConverterFactory;
import com.example.puzzlesolver.models.factories.PuzzleFactory;
import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.IconDialogSettings;
import com.maltaisn.icondialog.data.Icon;
import com.maltaisn.icondialog.pack.IconPack;

import java.util.List;

public class ConverterScreen extends AppCompatActivity implements IconDialog.Callback {

    private static final String ICON_DIALOG_TAG = "icon-dialog";

    //Private data members to hold view components
    private TextView puzzle_output;

    //Holds references to the four puzzle inputs
    private AppCompatImageView puzzle_input_1;
    private AppCompatImageView puzzle_input_2;
    private AppCompatImageView puzzle_input_3;
    private AppCompatImageView puzzle_input_4;

    //Holds a reference to the currently accessed image view
    private AppCompatImageView current_view;

    private Drawable icon_tray;
    private Button solve_btn;
    private StringBuilder sb;
    private PuzzleFactory puzzleFactory;

    private IconDialog iconDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_layout);

        //Initialize new puzzle factory for converters
        puzzleFactory = new ConverterFactory();

        //Initialize StringBuilder
        sb = new StringBuilder();

        //Get solve button from view
        solve_btn = (Button) findViewById(R.id.solve_puzzle);

        //Get text view for solution output
        puzzle_output = (TextView) findViewById(R.id.puzzle_solution);

        //Get references to the four puzzle view components
        puzzle_input_1 = (AppCompatImageView) findViewById(R.id.puzzle_input_1);
        puzzle_input_2 = (AppCompatImageView) findViewById(R.id.puzzle_input_2);
        puzzle_input_3 = (AppCompatImageView) findViewById(R.id.puzzle_input_3);
        puzzle_input_4 = (AppCompatImageView) findViewById(R.id.puzzle_input_4);

        //Setup new button listener
        solve_btn.setOnClickListener(solve_listener);

        // If dialog is already added to fragment manager, get it. If not, create a new instance.
        IconDialog dialog = (IconDialog) getSupportFragmentManager().findFragmentByTag(ICON_DIALOG_TAG);
        iconDialog = dialog != null ? dialog
                : IconDialog.newInstance(new IconDialogSettings.Builder().build());

        registerListeners();
        /*
        Button btn = findViewById(R.id.open_btn);
        btn.setOnClickListener(v -> {
            // Open icon dialog
            iconDialog.show(getSupportFragmentManager(), ICON_DIALOG_TAG);
        });
         */
    }

    //Setup "on click" listeners for the image views
    private void registerListeners()
    {
        puzzle_input_1.setOnClickListener(input_tap_listener);
        puzzle_input_2.setOnClickListener(input_tap_listener);
        puzzle_input_3.setOnClickListener(input_tap_listener);
        puzzle_input_4.setOnClickListener(input_tap_listener);
    }

    private View.OnClickListener input_tap_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            current_view = (AppCompatImageView) view;
            iconDialog.show(getSupportFragmentManager(), ICON_DIALOG_TAG);
        }
    };

    @Nullable
    @Override
    public IconPack getIconDialogIconPack() {
        return ((ConverterState) getApplication()).getIconPack();
    }

    @Override
    public void onIconDialogIconsSelected(@NonNull IconDialog dialog, @NonNull List<Icon> icons) {
        //Append icon id to end of string
        for (Icon icon : icons) {
            sb.append(icon.getId());
            sb.append(" ");
        }

        //Drawable image will store the current image and the new image to be appended
        LayerDrawable appended_img = new LayerDrawable(new Drawable[] { current_view.getDrawable(), icons.get(0).getDrawable() });

        //Append the image to the current image
        //This code must be placed inside this block to ensure compatibility, may be changed later
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            appended_img.setLayerInsetRight(0, icons.get(0).getDrawable().getIntrinsicWidth());
            appended_img.setLayerGravity(1, Gravity.AXIS_PULL_AFTER);
        }

        //Set the image
        current_view.setImageDrawable(appended_img);
    }

    //Solve puzzle on click listener
    private View.OnClickListener solve_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Get puzzle input string from the StringBuilder
            String puzzle_input_string = sb.toString();

            //Convert puzzle input to puzzle
            Puzzle puzzle = puzzleFactory.createPuzzle(puzzle_input_string);

            //Check if a puzzle matching the input was found, if not print an error message
            if (puzzle == null)
                puzzle_output.setText("Solution: " + "Invalid Puzzle!");
            else
            {
                //If puzzle was found setup the problem and find a solution
                puzzle.setProblem(puzzle_input_string);

                //Once the puzzle is setup does it have a solution?
                //If not print an error message
                if (puzzle.findSolution() == null)
                    puzzle_output.setText("Solution: " + "None Found!");
                else
                    puzzle_output.setText("Solution: " + puzzle.findSolution());
            }
        }
    };

    @Override
    public void onIconDialogCancelled() {}
}
