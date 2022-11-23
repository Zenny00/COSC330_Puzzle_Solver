package com.example.puzzlesolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

        converter_button = (Button) findViewById(R.id.converter_btn);
        converter_button.setOnClickListener(converter_app);
    }

    View.OnClickListener converter_app = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent converter_intent = new Intent(com.example.puzzlesolver.MainMenu.this, com.example.puzzlesolver.ConverterScreen.class);
            startActivity(converter_intent);
        }
    };
}