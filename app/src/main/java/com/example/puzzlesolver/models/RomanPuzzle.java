package com.example.puzzlesolver.models;

import android.util.Log;

public class RomanPuzzle implements Puzzle {
    String accepted_roman[] = {"400", "401", "402", "403", "404", "405", "406", "407", "408"};
    String accepted_roman_values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String roman_values[];

    @Override
    public boolean isSolvable() {
        //If any of the spaces are empty the puzzle has no solution
        for (String value : roman_values)
            if (value.isEmpty())
                return false;

        //If each input String had a value there is a possible solution
        return true;
    }

    @Override
    public void setProblem(String input) {
        //Split the input string on the "-" delimiter
        String input_values[] = input.split("-");

        //Loop through the entire list of characters, not special values needed
        //Convert values from ids to the correct character
        for (int i = 0; i < input_values.length; i++)
            input_values[i] = extractRoman(input_values[i]);

        roman_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each decimal to the solution
        for (String decimal : roman_values)
            solution += decimal + " ";

        return solution;
    }

    //Get roman numeral values from the input String
    private String extractRoman(String input)
    {
        //Get current string
        String roman_value = input;
        //Remove spaces
        roman_value = roman_value.replace(" ", "");

        //Loop through each roman numeral and get its decimal value
        for (int i = 0; i < accepted_roman.length; i++) {
            //Replace numeral with the proper value
            if (roman_value.equals(accepted_roman[i]))
                roman_value = accepted_roman_values[i];
        }

        //Return the extracted String
        return roman_value;
    }
}
