package com.example.puzzlesolver.models;

import android.util.Log;

public class BinaryPuzzle implements Puzzle {
    String binary_values[];

    @Override
    public boolean isSolvable() {
        return true;
    }

    @Override
    public void setProblem(String input) {
        //Split values on spaces
        binary_values = input.split("0");

        Log.d("Binary values Length", String.valueOf(binary_values.length));

        //Convert values
        for (int j = 0; j < binary_values.length; j++)
        {
            //Get current string
            String binary = binary_values[j];

            //Replace icon ids with their corresponding values
            binary = binary.replace('5', '0');
            binary = binary.replace('6', '1');
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
