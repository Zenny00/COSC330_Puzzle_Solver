//Name: Joshua Comfort
//Date: 11/22/2022-12/10/2022
//Description: Arrow Grid Puzzle similar to a numpad on a keyboard

package com.example.puzzlesolver.models;

import android.util.Log;

public class ArrowGridPuzzle extends ConverterPuzzle {
    //Call the superclass constructor to build a new converter puzzle
    public ArrowGridPuzzle()
    {
        super(new String[]{"300", "301", "302", "303", "304", "305", "306", "307", "308", "1", "2", "3", "4"}, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "l", "r", "u", "d"});
    }

    @Override
    public boolean isSolvable() {
        //Initialize the starting value to be out of range
        int starting_value = -1;

        //Check if the first value contains a digit
        try
        {
            starting_value = Integer.parseInt(output_values[0]);
        } catch (NumberFormatException e)
        {
            return false;
        }

        //If the character is a digit make sure it is within the allowed ranges
        if (starting_value < 1 || starting_value > 9)
            return false;

        //If any of the spaces are empty the puzzle has no solution
        for (String value : output_values)
            if (value.isEmpty())
                return false;

        //If each input String had a value there is a possible solution
        return true;
    }

    @Override
    public String findSolution() {
        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each value to and output String
        String solution = "";

        //Holds the initial value
        int value = Integer.parseInt(output_values[0]);

        //Holds the indexes for the current value
        int num_row = -1;
        int num_col = -1;

        //2D grid to hold the NumPad
        int grid[][] = {{7, 8, 9},
                        {4, 5, 6},
                        {1, 2, 3}};

        //Loop through the number line till we find the starting value
        //If the value is found, then save the index
        //Loop through the NumPad
        for (int row = 0; row < grid.length; row++)
            for (int column = 0; column < grid[row].length; column++)
                //Get the coordinates of the value if it exists within the 2D array
                if (grid[row][column] == value){
                    num_row = row;
                    num_col = column;
                }

        //Append the first value to the solution string
        solution += String.valueOf(value) + " ";

        //Loop through each arrow value starting at index 1 (second value input)
        for (int i = 1; i < output_values.length; i++)
        {
            //Get the current string (arrow value)
            String current = output_values[i];

            if (current.equals("r"))
            {
                //Move left on the NumPad
                //Move right on the NumPad
                num_col = num_col + 1;
                if (num_col > 2)
                    num_col = 0;
            }
            else if (current.equals("l")) {
                //Move right on the NumPad
                num_col = num_col - 1;
                if (num_col < 0)
                    num_col = 2;
            }
            else if (current.equals("u")) {
                //Move up on the NumPad
                num_row = num_row - 1;
                if (num_row < 0)
                    num_row = 2;
            }
            else if (current.equals("d")) {
                //Move down on the NumPad
                num_row = num_row + 1;
                if (num_row > 2)
                    num_row = 0;
            }

            //Append the current value to the solution string
            solution += String.valueOf(grid[num_row][num_col]) + " ";
        }

        //Return the solution
        return solution;
    }

    //Get arrow values from the input String
    @Override
    protected String extractValues(String input)
    {
        //Get current string
        String arrow_value = input;
        //Remove spaces
        arrow_value = arrow_value.replace(" ", "");

        //Loop through each id and get its value
        for (int i = 0; i < accepted_ids.length; i++) {
            //Replace numeral with the proper value
            if (arrow_value.equals(accepted_ids[i])) {
                arrow_value = accepted_values[i];
                break; //Break out of the loop to prevent the value from being converted again
            }
        }

        //Return the extracted String
        return arrow_value;
    }
}
