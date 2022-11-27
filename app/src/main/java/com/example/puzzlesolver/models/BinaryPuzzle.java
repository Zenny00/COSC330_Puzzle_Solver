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
            String binary = binary_values[j];
            /*
            for (int i = 0; i < binary.length(); i++) {
                //Replace 5s with 0s
                if (binary.charAt(i) == '5')
                    binary = binary.substring(0, i) + '0' + binary.substring(i + 1);
                //Replace 6s with 1s
                else if (binary.charAt(i) == '6')
                    binary = binary.substring(0, i) + '1' + binary.substring(i + 1);
                //Remove spaces
                else if (binary.charAt(i) == ' ')
                    binary = binary.substring(0, i) + binary.substring(i + 1);
            }
             */

            binary = binary.replace('5', '0');
            binary = binary.replace('6', '1');
            binary = binary.replace(" ", "");

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
