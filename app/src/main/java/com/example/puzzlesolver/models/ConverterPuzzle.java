package com.example.puzzlesolver.models;

//Super class that can be overridden by subclasses
public class ConverterPuzzle implements Puzzle {
    private String accepted_ids[] = {"700", "701", "702", "703", "704", "705", "706", "707", "708", "709"}; //ID 0
    private String accepted_values[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String output_values[];

    public ConverterPuzzle(String accepted_ids[], String accepted_values[])
    {
        this.accepted_ids = accepted_ids;
        this.accepted_values = accepted_values;
    }

    @Override
    public boolean isSolvable() {
        //If any of the spaces are empty the puzzle has no solution
        for (String value : output_values)
            if (value.isEmpty())
                return false;

        //If each input String had a value there is a possible solution
        return true;
    }

    @Override
    public void setProblem(String input) {
        //Split the input string on the "-" delimiter
        String input_values[] = input.split("-");

        //Loop through the entire list of ids, no special values needed
        //Convert values from ids to the correct character
        for (int i = 0; i < input_values.length; i++)
            input_values[i] = extractValues(input_values[i]);

        output_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each decimal to the solution
        for (String character : output_values)
            solution += character + " ";

        return solution;
    }

    //Get character values from the input String
    private String extractValues(String input)
    {
        //Get current string
        String binary_value = input;
        //Remove spaces
        binary_value = binary_value.replace(" ", "");

        //Loop through each decimal and get its character value
        for (int i = 0; i < accepted_ids.length; i++) {
            //Replace decimal with the proper value
            if (binary_value.equals(accepted_ids[i]))
                binary_value = accepted_values[i];
        }

        //Return the extracted String
        return binary_value;
    }
}
