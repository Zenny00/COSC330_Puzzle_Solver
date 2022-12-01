package com.example.puzzlesolver.models;

public class LinePuzzle implements Puzzle {
    String accepted_line[] = {"800", "801", "802", "803", "804", "805", "806", "807", "808"}; //ID 8
    String accepted_line_values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String line_values[];

    @Override
    public boolean isSolvable() {
        //If any of the spaces are empty the puzzle has no solution
        for (String value : line_values)
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
            input_values[i] = extractShape(input_values[i]);

        line_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each decimal to the solution
        for (String decimal : line_values)
            solution += decimal + " ";

        return solution;
    }

    //Get roman numeral values from the input String
    private String extractShape(String input)
    {
        //Get current string
        String shape_value = input;
        //Remove spaces
        shape_value = shape_value.replace(" ", "");

        //Loop through each line drawing and get its decimal value
        for (int i = 0; i < accepted_line.length; i++) {
            //Replace number of lines with the proper value
            if (shape_value.equals(accepted_line[i]))
                shape_value = accepted_line_values[i];
        }

        //Return the extracted String
        return shape_value;
    }
}
