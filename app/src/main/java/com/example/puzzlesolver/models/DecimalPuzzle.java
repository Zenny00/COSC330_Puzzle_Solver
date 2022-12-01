package com.example.puzzlesolver.models;

public class DecimalPuzzle implements Puzzle {
    String accepted_decimal[] = {"300", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "314", "315", "316", "317", "318", "319", "320", "321", "322", "323", "324", "325"}; //ID 6
    String accepted_decimal_values[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String character_values[];

    @Override
    public boolean isSolvable() {
        //If any of the spaces are empty the puzzle has no solution
        for (String value : character_values)
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
            input_values[i] = extractDecimals(input_values[i]);

        character_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each decimal to the solution
        for (String character : character_values)
            solution += character + " ";

        return solution;
    }

    //Get character values from the input String
    private String extractDecimals(String input)
    {
        //Get current string
        String decimal_value = input;
        //Remove spaces
        decimal_value = decimal_value.replace(" ", "");

        //Loop through each decimal and get its character value
        for (int i = 0; i < accepted_decimal.length; i++) {
            //Replace decimal with the proper value
            if (decimal_value.equals(accepted_decimal[i]))
                decimal_value = accepted_decimal_values[i];
        }

        //Return the extracted String
        return decimal_value;
    }
}
