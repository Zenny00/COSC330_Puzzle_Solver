package com.example.puzzlesolver.models;

import android.util.Log;

public class MorsePuzzle implements Puzzle {

    //Array of strings to hold the morse values
    //Starting at A ending with 9
    private String accepted_morse[] = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134"};
    private String accepted_morse_values[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String morse_values[];

    @Override
    public boolean isSolvable() {
        return false;
    }

    @Override
    public void setProblem(String input) {
        //Split the input string on the "-" delimiter
        String input_values[] = input.split("-");

        //Loop through the entire list of characters, not special values needed
        //Convert values from ids to the correct character
        for (int i = 0; i < input_values.length; i++)
            input_values[i] = extractMorse(input_values[i]);

        morse_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        for (String character : morse_values)
            solution += character + " ";

        return solution;
    };

    //Get arrow values from the input String
    private String extractMorse(String input)
    {
        //Get current string
        String morse_value = input;
        //Remove spaces
        morse_value = morse_value.replace(" ", "");

        //Loop through each morse value and get its decoded value
        for (int i = 0; i < accepted_morse.length; i++) {
            Log.d("Input: ", input);
            Log.d("Output: ", accepted_morse[i]);
            //Replace morse with the proper character
            if (morse_value.equals(accepted_morse[i]))
                morse_value = accepted_morse_values[i];
        }

        //Replace the string at the given index
        return morse_value;
    }
}
