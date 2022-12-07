package com.example.puzzlesolver.models;

import android.util.Log;

public class ArrowLinePuzzle implements Puzzle {
    String accepted_arrow_line[] = {"300", "301", "302", "303", "304", "305", "306", "307", "308", "1", "2", "5", "6", "7", "8"}; //ID 4
    String accepted_arrow_line_values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "l", "r", "ll", "rr", "lll", "rrr"}; //Icon values
    String arrow_values[];

    @Override
    public boolean isSolvable() {
        //Initialize the starting value to be out of range
        int starting_value = -1;

        //Check if the first value contains a digit
        try
        {
            starting_value = Integer.parseInt(arrow_values[0]);
        } catch (NumberFormatException e)
        {
            return false;
        }

        //If the character is a digit make sure it is within the allowed ranges
        if (starting_value < 1 || starting_value > 9)
            return false;

        //If any of the spaces are empty the puzzle has no solution
        for (String value : arrow_values)
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
        for (int i = 0; i < input_values.length; i++) {
            input_values[i] = extractArrow(input_values[i]);
        }

        arrow_values = input_values;
    }

    @Override
    public String findSolution() {
        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each value to and output String
        String solution = "";

        //Get the starting value
        int value = Integer.parseInt(arrow_values[0]);

        //Index of the initial value
        int index = -1;

        //Line of the numbers that arrows will be used to seek through
        int number_line[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

        //Loop through the number line till we find the starting value
        //If the value is found, then save the index
        for (int i = 0; i < number_line.length; i++)
            if (number_line[i] == value)
                index = i;

        //Append the first value to the solution string
        solution += String.valueOf(value) + " ";

        //Loop through each arrow value starting at index 1 (second value input)
        for (int i = 1; i < arrow_values.length; i++)
        {
            //Get the current string (arrow value)
            String current = arrow_values[i];

            Log.d("Before", String.valueOf(index));

            if (current.equals("l")) {
                index = (index - 1); //Move left across the number line by one space
                if (index < 0) //If the number is out of bounds move to the other side
                    index = number_line.length - Math.abs(index);
            }
            else if (current.equals("r")) {
                index = (index + 1); //Move right across the number line by one space
                if (index > 9) //If the number is out of bounds move to the other side
                    index = (index - number_line.length);
            }
            else if (current.equals("ll")) {
                index = (index - 2); //Move left across the number line by two spaces
                if (index < 0) //If the number is out of bounds move to the other side
                    index = number_line.length - Math.abs(index);
            }
            else if (current.equals("rr")) {
                index = (index + 2); //Move right across the number line by two spaces
                if (index > 9) //If the number is out of bounds move to the other side
                    index = (index - number_line.length);
            }
            else if (current.equals("lll")) {
                index = (index - 3); //Move left across the number line by three spaces
                if (index < 0) //If the number is out of bounds move to the other side
                    index = number_line.length - Math.abs(index);
            }
            else if (current.equals("rrr")) {
                index = (index + 3); //Move right across the number line by three spaces
                if (index > 9) //If the number is out of bounds move to the other side
                    index = (index - number_line.length);
            }

            Log.d("After", String.valueOf(index));

            //Append the current value to the solution string
            solution += String.valueOf(number_line[index]) + " ";
        }

        //Return the solution
        return solution;
    }

    //Get roman numeral values from the input String
    private String extractArrow(String input)
    {
        //Get current string
        String arrow_value = input;
        //Remove spaces
        arrow_value = arrow_value.replace(" ", "");

        //Loop through each roman numeral and get its decimal value
        for (int i = 0; i < accepted_arrow_line.length; i++) {
            //Replace numeral with the proper value
            if (arrow_value.equals(accepted_arrow_line[i])) {
                arrow_value = accepted_arrow_line_values[i];
                break; //Break out of the loop to prevent the value from being converted again
            }
        }

        //Return the extracted String
        return arrow_value;
    }
}
