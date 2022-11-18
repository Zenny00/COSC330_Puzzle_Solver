package com.example.puzzlesolver;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    //Private data members corresponding to the buttons on screen
    private Button converter_button;
    private Button word_button;
    private Button lights_out_button;
    private Button slider_button;

    //On menu creation
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
    }
}