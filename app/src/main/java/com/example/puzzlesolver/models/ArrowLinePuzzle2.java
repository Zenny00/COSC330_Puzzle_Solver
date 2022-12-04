package com.example.puzzlesolver.models;

public class ArrowLinePuzzle2 implements Puzzle {
    String accepted_arrow_line[] = {"300", "301", "302", "303", "304", "305", "306", "307", "308", "1", "2", "5", "6", "7", "8"}; //ID 4
    String accepted_arrow_line_values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "l", "r"};
    String arrow_values[];

    @Override
    public boolean isSolvable() {
        //Check if the first value contains a digit
        char characterArray[] = arrow_values[0].toCharArray();
        for (char c : characterArray)
            if (! Character.isDigit(c))
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
        for (int i = 0; i < input_values.length; i++)
            input_values[i] = extractRoman(input_values[i]);

        arrow_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each decimal to the solution
        for (String decimal : arrow_values)
            solution += decimal + " ";

        return solution;
    }

    //Get roman numeral values from the input String
    private String extractRoman(String input)
    {
        //Get current string
        String roman_value = input;
        //Remove spaces
        roman_value = roman_value.replace(" ", "");

        //Loop through each roman numeral and get its decimal value
        for (int i = 0; i < accepted_arrow_line.length; i++) {
            //Replace numeral with the proper value
            if (roman_value.equals(accepted_arrow_line[i]))
                roman_value = accepted_arrow_line_values[i];
        }

        //Return the extracted String
        return roman_value;
    }
}
