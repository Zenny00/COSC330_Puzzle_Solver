//Name: Joshua Comfort
//Date: 11/22/2022-12/10/2022
//Description: Converter puzzle solver activity, use the icon dialog picker to allow users to input custom icons

package com.example.puzzlesolver;

import android.animation.Animator;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
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

public class ConverterScreen extends PuzzleSolverTemplate implements IconDialog.Callback {

    private static final String ICON_DIALOG_TAG = "icon-dialog";

    //Private data members to hold view components
    private TextView puzzle_output;
    private ImageView lock_icon;

    //Holds references to the four puzzle inputs
    private AppCompatImageView puzzle_input_1;
    private AppCompatImageView puzzle_input_2;
    private AppCompatImageView puzzle_input_3;
    private AppCompatImageView puzzle_input_4;

    //Holds a reference to the currently accessed image view
    private AppCompatImageView current_view;

    //String for each image view
    private String input_string_1 = "";
    private String input_string_2 = "";
    private String input_string_3 = "";
    private String input_string_4 = "";

    private int id;

    private Drawable icon_tray;
    private Button solve_btn;
    private PuzzleFactory puzzleFactory;

    //Audio player for playing the lock and unlock sound effect
    private MediaPlayer mp;
    private String input_string;

    private IconDialog iconDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_layout);

        //Initialize new puzzle factory for converters
        puzzleFactory = new ConverterFactory();

        //Get reference to the lock icon
        lock_icon = (ImageView) findViewById(R.id.lock_icon);

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
        //solve_btn.setOnClickListener(solve_listener);

        // If dialog is already added to fragment manager, get it. If not, create a new instance.
        IconDialog dialog = (IconDialog) getSupportFragmentManager().findFragmentByTag(ICON_DIALOG_TAG);
        iconDialog = dialog != null ? dialog
                : IconDialog.newInstance(new IconDialogSettings.Builder().build());
    }

    //Function called by the inputs
    public void dialog_input(View view)
    {
        //Get the id of the current view
        current_view = (AppCompatImageView) view;
        id = current_view.getId();

        //Show the dialog box
        iconDialog.show(getSupportFragmentManager(), ICON_DIALOG_TAG);
    }

    @Nullable
    @Override
    public IconPack getIconDialogIconPack() {
        return ((ConverterState) getApplication()).getIconPack();
    }

    @Override
    public void onIconDialogIconsSelected(@NonNull IconDialog dialog, @NonNull List<Icon> icons) {
        //Append icon id to end of string
        for (Icon icon : icons) {
            switch (id)
            {
                case R.id.puzzle_input_1:
                    input_string_1 = icon.getId() + " ";
                    break;
                case R.id.puzzle_input_2:
                    input_string_2 = icon.getId() + " ";
                    break;
                case R.id.puzzle_input_3:
                    input_string_3 = icon.getId() + " ";
                    break;
                case R.id.puzzle_input_4:
                    input_string_4 = icon.getId() + " ";
                    break;
            }
        }

        //Set the image
        current_view.setImageDrawable(icons.get(0).getDrawable());
    }

    public void clearPuzzle(View view)
    {
        //Setup new media player to play the lock sound effect
        mp = MediaPlayer.create(this, R.raw.lock_locked);
        //Play lock sound effect
        mp.start();

        //Clear the image views
        puzzle_input_1.setImageResource(0);
        puzzle_input_2.setImageResource(0);
        puzzle_input_3.setImageResource(0);
        puzzle_input_4.setImageResource(0);

        //Clear input Strings
        input_string_1 = "";
        input_string_2 = "";
        input_string_3 = "";
        input_string_4 = "";

        //Reset the lock icon
        if (lock_icon.getDrawable().getConstantState().equals(lock_icon.getContext().getDrawable(R.drawable.lock_unlock_icon).getConstantState()))
            clear_animation(lock_icon);

        //Reset solution
        puzzle_output.setText("Solution:");
    }

    //Shake the lock icon around for 1 second
    private void shake_animation(ImageView img) {
        TranslateAnimation anim = new TranslateAnimation(-25.0f, 25.0f, 0.0f, 0.0f);
        anim.setDuration(100);
        anim.setRepeatCount(3);
        anim.setRepeatMode(2);

        img.startAnimation(anim);
    }

    //Rotate the lock icon around the Y-axis with a duration of .5 seconds
    private void rotate_animation(ImageView img)
    {
        img.animate();
        img.animate().setDuration(500);
        img.animate().rotationYBy(360f);

        //Animation Listener
        img.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) { }

            @Override
            public void onAnimationEnd(Animator animator) {
                //When the animation ends, change the icon
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    img.setImageDrawable(getDrawable(R.drawable.lock_unlock_icon));
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) { }

            @Override
            public void onAnimationRepeat(Animator animator) {}
        }).start();
    }

    //Rotate the lock icon around the Y-axis with a duration of .5 seconds
    private void clear_animation(ImageView img)
    {
        //Spin the lock around
        img.animate();
        img.animate().setDuration(500);
        img.animate().rotationYBy(360f);

        //Animation Listener
        img.animate().setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) { }

            @Override
            public void onAnimationEnd(Animator animator) {
                //When the animation ends, change the icon
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    img.setImageDrawable(getDrawable(R.drawable.lock_img));
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) { }

            @Override
            public void onAnimationRepeat(Animator animator) {}
        }).start();
    }

    @Override
    public void onIconDialogCancelled() {}

    //Back function, ends the intent
    public void end_converter(View view)
    {
        //Finish the intent
        finish();
    }

    public void callSolver(View view)
    {
        //Call the solve problem function from the superclass
        solveProblem();
    }

    @Override
    public void readProblem() {
        //Get puzzle input string from the StringBuilders
        input_string = input_string_1.trim() + "-" + input_string_2.trim() + "-" + input_string_3.trim() + "-" + input_string_4.trim();

        //Convert puzzle input to puzzle
        problem = puzzleFactory.createPuzzle(input_string);

        //If puzzle was found setup the problem and find a solution
        problem.setProblem(input_string);
    }

    @Override
    public void outputError() {
        puzzle_output.setText("Solution: " + "None Found!");
        shake_animation(lock_icon);

        //Setup new media player to play the stuck sound effect
        mp = MediaPlayer.create(this, R.raw.lock_stuck);
        //Play stuck sound effect
        mp.start();
    }

    @Override
    public void outputSolution() {
        puzzle_output.setText("Solution: " + problem.findSolution());
        rotate_animation(lock_icon);

        //Setup new media player to play the unlock sound effect
        mp = MediaPlayer.create(this, R.raw.lock_unlocked);
        //Play unlock sound effect
        mp.start();
    }
}
