package com.example.puzzlesolver.models;

import android.util.Log;

public class ArrowGridPuzzle implements Puzzle {
    String arrow_values[];

    private final String accepted_arrow_grid[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 5
    private final String accepted_arrow_gird_values[] = {" ", "l", "r", "u", "d", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @Override
    public boolean isSolvable() {
        //Check if the string contains an integer
        boolean hasSolution = false;

        try {
            Integer.parseInt(arrow_values[0]);
            hasSolution = true;
        } catch (NumberFormatException e)
        {
            hasSolution = false;
        }

        return hasSolution;
    }

    @Override
    public void setProblem(String input) {
        //Split the input string on the "-" delimiter
        String input_values[] = input.split("-");

        //Get the number from the first input section
        input_values[0] = extractNumber(input_values[0]);

        //Convert values from id to the correct character
        for (int i = 1; i < input_values.length; i++)
            input_values[i] = extractArrows(input_values[i]);

        arrow_values = input_values;
    }

    @Override
    public String findSolution() {
        //Check if the puzzle has a solution
        if (!isSolvable())
            return null;

        int value = Integer.parseInt(arrow_values[0]);

        int num_row = 0;
        int num_col = 0;

        //2D grid to hold the NumPad
        int grid[][] = {{7, 8, 9},
                        {4, 5, 6},
                        {1, 2, 3}};

        Log.d("Number", String.valueOf(value));
        Log.d("Arr Length", String.valueOf(grid.length));

        //Loop through the NumPad
        for (int row = 0; row < grid.length - 1; row++)
            for (int column = 0; column < grid[column].length - 1; column++)
                //Get the coordinates of the value if it exists within the 2D array
                if (grid[row][column] == value){
                    num_row = row;
                    num_col = column;
                }

        Log.d("Row", String.valueOf(num_row));
        Log.d("Col", String.valueOf(num_col));

        //Loop through binary values and convert to decimal
        for (int i = 1; i < arrow_values.length; i++)
        {
            //Iterate through each of the other boxes
            //Add each time there is an up arrow, subtract each time there is a down arrow
            //Loop through each sub-string within the string to use its value
            String arrow_value[] = arrow_values[i].split(" ");

            for (String sub_string : arrow_value) {
                if (sub_string.equals("r"))
                    num_col = Math.abs((num_col + 1) % 3); //Move left on the NumPad
                else if (sub_string.equals("l"))
                    num_col = Math.abs((num_col - 1) % 3); //Move right on the NumPad
                else if (sub_string.equals("u"))
                    num_row = Math.abs((num_row - 1) % 3); //Move up on the NumPad
                else if (sub_string.equals("d"))
                    num_row = Math.abs((num_row + 1) % 3); //Move down on the NumPad

            }
        }

        //Return the result
        return String.valueOf(grid[num_row][num_col]);
    }

    //Get number values from the input String
    private String extractNumber(String input)
    {
        //Holds the index of the number value, if it doesn't change the value is invalid
        int index_of_number = -1;

        //Get each number from the first box
        String sub_values[] = input.split(" ");
        String final_number = "";

        //Loop through each of the values at index 0
        for (String number : sub_values)
        {
            //Loop through the array of accepted characters
            for (int i = 0; i < accepted_arrow_grid.length; i++)
                //Find the index of the number character
                if (accepted_arrow_grid[i].equals(number))
                    //If the value is found, get it's index
                    index_of_number = i;

            //Only run if we found a character
            if (index_of_number != -1) {
                //Set the id equal to its value
                number = accepted_arrow_gird_values[index_of_number];

                //Append each new number to the String
                final_number += number;
            }
        }

        //Return the string containing the final number
        return final_number;
    }

    //Get arrow values from the input String
    private String extractArrows(String input)
    {
        //Get current string
        String arrow_value = input;
        //Remove spaces
        arrow_value = arrow_value.replace(" ", "");

        //Replace icon ids with their corresponding values
        for (int j = 0; j < accepted_arrow_grid.length; j++)
            arrow_value = arrow_value.replace(accepted_arrow_grid[j], accepted_arrow_gird_values[j]);

        //Replace the string at the given index
        return arrow_value;
    }
}
