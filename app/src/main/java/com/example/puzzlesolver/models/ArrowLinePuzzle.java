package com.example.puzzlesolver.models;

import android.util.Log;

public class ArrowLinePuzzle implements Puzzle {
    String arrow_values[];

    private final String accepted_arrow_line[] = {"0", "1", "2", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
    private final String accepted_arrow_line_values[] = {" ", "-", "+", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    @Override
    public boolean isSolvable() {
        //Check if the string contains an integer
        boolean hasSolution = false;

        int value = 0;
        try {
            value = Integer.parseInt(arrow_values[0]);
            hasSolution = true;
        } catch (NumberFormatException e)
        {
            hasSolution = false;
        }

        if ((value < 1 || value > 9))
            hasSolution = false;

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

        //Extract the integer value from the input String
        int value = Integer.parseInt(arrow_values[0]);

        String solution = "";
        //Add first value
        solution += String.valueOf(value) + " ";

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

            //Add each value
            solution += String.valueOf(value) + " ";
        }

        //Return the result
        return solution;
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
            for (int i = 0; i < accepted_arrow_line.length; i++)
                //Find the index of the number character
                if (accepted_arrow_line[i].equals(number))
                    //If the value is found, get it's index
                    index_of_number = i;

            //Only run if we found a character
            if (index_of_number != -1) {
                //Set the id equal to its value
                number = accepted_arrow_line_values[index_of_number];

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
        for (int j = 0; j < accepted_arrow_line.length; j++)
            arrow_value = arrow_value.replace(accepted_arrow_line[j], accepted_arrow_line_values[j]);

        //Replace the string at the given index
        return arrow_value;
    }
}
