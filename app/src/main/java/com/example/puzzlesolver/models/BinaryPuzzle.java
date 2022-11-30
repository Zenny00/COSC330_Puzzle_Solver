package com.example.puzzlesolver.models;

import android.util.Log;

import java.util.ArrayList;

public class BinaryPuzzle implements Puzzle {
    //Split Strings
    String binary_values[];

    //String of accepted characters
    private final String accepted_binary[] = {"700", "701"};
    private final String accepted_binary_value[] = {"0", "1"};

    @Override
    public boolean isSolvable() {
        boolean isSolvable = true;

        /*
        //Check if there is at least one non empty String
        for (String binary : binary_values)
        {
            if (!binary.equals(""))
                isSolvable = true;
        }
         */

        return isSolvable;
    }

    @Override
    public void setProblem(String input) {
        //Split values on spaces
        binary_values = input.split("-");

        //Convert values
        for (int j = 0; j < binary_values.length; j++)
        {
            //Get current string
            String binary = binary_values[j];
            //Replace icon ids with their corresponding values
            binary = binary.replace("700", "0");
            binary = binary.replace("701", "1");
            binary = binary.replace(" ", "");

            //Replace the string at the given index
            binary_values[j] = binary;
        }
    }

    @Override
    public String findSolution() {
        String solution = "";

        if (!isSolvable())
            return null;

        //Loop through binary values and convert to decimal
        for (int i = 0; i < binary_values.length; i++)
        {
            Log.d("Binary Strings: ", binary_values[i]);
            String binary = binary_values[i];
            Log.d("Binary", binary);
            //Get the decimal value from the binary and append it to the solution string
            Integer decimal = Integer.parseInt(binary, 2);
            solution += decimal.toString() + " ";
        }

        //Return the solution
        return solution;
    }
}