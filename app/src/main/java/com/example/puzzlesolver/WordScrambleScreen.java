package com.example.puzzlesolver;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
import com.example.puzzlesolver.models.factories.WordScrambleFactory;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.IconDialogSettings;
import com.maltaisn.icondialog.data.Icon;
import com.maltaisn.icondialog.pack.IconPack;

import java.util.List;

public class WordScrambleScreen extends AppCompatActivity {
    //Private data members to hold view components
    private TextView puzzle_output;
    private ImageView lock_icon;
    private ParseWords ps;

    //Holds references to the four puzzle inputs
    private TextInputEditText puzzle_input_1;

    //Holds a reference to the currently accessed image view
    private AppCompatImageView current_view;

    //String for each image view
    private String input_string_1 = "";

    private int id;

    private Drawable icon_tray;
    private Button solve_btn;
    private PuzzleFactory puzzleFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordscramble_layout);

        //Initialize new puzzle factory for converters
        puzzleFactory = new WordScrambleFactory();

        //Get reference to the lock icon
        lock_icon = (ImageView) findViewById(R.id.lock_icon);

        //Get solve button from view
        solve_btn = (Button) findViewById(R.id.solve_puzzle);

        //Get text view for solution output
        puzzle_output = (TextView) findViewById(R.id.puzzle_solution);

        //Get references to the four puzzle view components
        puzzle_input_1 = (TextInputEditText) findViewById(R.id.Input);


        //Setup new button listener
        solve_btn.setOnClickListener(solve_listener);

        /*puzzle_input_1.setOnEditorActionListener(
                new TextView.OnEditorActionListener(){
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
                    {
                        v.setSingleLine(true);
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            if(v.getText().length() > 0)
                            {
                                String perm_string = v.getText().toString();

                                ps = new ParseWords(perm_string);
                                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                            }
                            return true;
                        }
                        return false;
                    }
                });*/

    }

    //Solve puzzle on click listener
    private View.OnClickListener solve_listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(puzzle_input_1.getText().length() > 0) {
                String perm_string = puzzle_input_1.getText().toString();

                ps = new ParseWords(perm_string);
                //InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                //imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                //Get puzzle input string from the StringBuilders
                puzzle_output.setText("Solution: " + ps.getPrinter());
            } else {
                puzzle_output.setText("No Text Entered");
            }
        }
    };

    public void clearPuzzle(View view)
    {
        puzzle_input_1.setText("");
        ps.clearPrinter();
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
}
