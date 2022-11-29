package com.example.puzzlesolver.models;

import android.util.Log;

public class ArrowLinePuzzle implements Puzzle {
    String arrow_values[];

    private final String accepted_arrow_line[] = {"0", "1", "2", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    private final String accepted_arrow_line_values[] = {" ", "-", "+", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @Override
    public boolean isSolvable() {
        //Check if the string contains an integer
        return arrow_values[0].matches("\\d+");
    }

    @Override
    public void setProblem(String input) {
        //Split the input string on the "-" delimiter
        arrow_values = input.split("-");

        //Convert values
        for (int j = 0; j < arrow_values.length; j++)
        {
            //Get current string
            String arrow_value = arrow_values[j];
            Log.d("Value", arrow_value);
            arrow_value = arrow_value.replace(" ", "");

            //Replace icon ids with their corresponding values
            for (int i = 0; i < accepted_arrow_line.length; i++)
                arrow_value = arrow_value.replace(accepted_arrow_line[i], accepted_arrow_line_values[i]);

            //Replace the string at the given index
            arrow_values[j] = arrow_value;
        }
    }

    @Override
    public String findSolution() {
        //Check if the puzzle has a solution
        if (!isSolvable())
            return null;

        int value = Integer.parseInt(arrow_values[0]);
        Log.d("Number", String.valueOf(value));

        //Loop through binary values and convert to decimal
        for (int i = 1; i < arrow_values.length; i++)
        {
            //Iterate through each of the other boxes
            //Add each time there is an up arrow, subtract each time there is a down arrow
            for (int j = 0; j < arrow_values[i].length(); j++) {
                if (arrow_values[i].charAt(j) == '+')
                    value++;
                else if (arrow_values[i].charAt(j) == '-')
                    value--;
            }
        }

        //Return the result
        return String.valueOf(value);
    }
}
