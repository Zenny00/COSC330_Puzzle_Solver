package com.example.puzzlesolver;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

    private TextView puzzle_output;
    private ImageView lock;
    private Drawable icon_tray;
    private Button solve_btn;
    private StringBuilder sb;
    private PuzzleFactory puzzleFactory;

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

        //Get lock view component
        lock = (ImageView) findViewById(R.id.puzzle_input);

        //Setup new button listener
        solve_btn.setOnClickListener(solve_listener);

        // If dialog is already added to fragment manager, get it. If not, create a new instance.
        IconDialog dialog = (IconDialog) getSupportFragmentManager().findFragmentByTag(ICON_DIALOG_TAG);
        IconDialog iconDialog = dialog != null ? dialog
                : IconDialog.newInstance(new IconDialogSettings.Builder().build());

        Button btn = findViewById(R.id.open_btn);
        btn.setOnClickListener(v -> {
            // Open icon dialog
            iconDialog.show(getSupportFragmentManager(), ICON_DIALOG_TAG);
        });
    }

    @Nullable
    @Override
    public IconPack getIconDialogIconPack() {
        return ((ConverterState) getApplication()).getIconPack();
    }

    @Override
    public void onIconDialogIconsSelected(@NonNull IconDialog dialog, @NonNull List<Icon> icons) {

        //Append icon to end of string
        for (Icon icon : icons) {
            sb.append(icon.getId());
            sb.append(" ");
        }

        //Drawable image will store the current image and the new image to be appended
        LayerDrawable appended_img = new LayerDrawable(new Drawable[] { lock.getDrawable(), icons.get(0).getDrawable() });

        //Append the image to the current image
        //This code must be placed inside this block to ensure compatibility, may be changed later
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            appended_img.setLayerInsetRight(0, icons.get(0).getDrawable().getIntrinsicWidth());
            appended_img.setLayerGravity(1, Gravity.AXIS_PULL_AFTER);
        }

        //Set the image
        lock.setImageDrawable(appended_img);
    }

    //Solve puzzle on click listener
    private View.OnClickListener solve_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Get puzzle input
            String puzzle_input = sb.toString();
            Log.d("Puzzle", puzzle_input);

            //Convert puzzle input to puzzle
            Puzzle puzzle = puzzleFactory.createPuzzle(puzzle_input);
            if (puzzle != null)
                Log.d("Puzzle Type", puzzle.getClass().toString());

            //Check if a puzzle was found
            if (puzzle == null)
                puzzle_output.setText("Solution not found!");
            else
            {
                //If puzzle was found setup the problem and find a solution
                puzzle.setProblem(puzzle_input);
                puzzle_output.setText(puzzle.findSolution());
            }
        }
    };

    @Override
    public void onIconDialogCancelled() {}
}
