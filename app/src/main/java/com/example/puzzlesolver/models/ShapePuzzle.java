package com.example.puzzlesolver.models;

public class ShapePuzzle implements Puzzle {
    String accepted_shape[] = {"200", "201", "202", "203", "204", "205", "206", "207", "208"}; //ID 3
    String accepted_shape_values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String shape_values[];

    @Override
    public boolean isSolvable() {
        //If any of the spaces are empty the puzzle has no solution
        for (String value : shape_values)
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
            input_values[i] = extractLine(input_values[i]);

        shape_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each decimal to the solution
        for (String decimal : shape_values)
            solution += decimal + " ";

        return solution;
    }

    //Get shapes values from the input String
    private String extractLine(String input)
    {
        //Get current string
        String line_value = input;
        //Remove spaces
        line_value = line_value.replace(" ", "");

        //Loop through each shape drawing and get its decimal value
        for (int i = 0; i < accepted_shape.length; i++) {
            //Replace number of shapes with the proper value
            if (line_value.equals(accepted_shape[i]))
                line_value = accepted_shape_values[i];
        }

        //Return the extracted String
        return line_value;
    }
}
