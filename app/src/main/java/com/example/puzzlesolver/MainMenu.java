package com.example.puzzlesolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
    }

    // BUTTON FUNCTIONS
    public void launchLightsActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), LightsOutActivity.class);
        startActivity(intent);
    }
    public void launchConverterActivity(View view) {

    }
    public void launchSliderActivity(View view) {

    }
    public void launchWordActivity(View view) {

    }

}