package com.example.puzzlesolver.models;

public class CharacterPuzzle implements Puzzle {
    String accepted_character[] = {"500", "501", "502", "503", "504", "505", "506", "507", "508"}; //ID 7
    String accepted_character_values[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String decimal_values[];

    @Override
    public boolean isSolvable() {
        //If any of the spaces are empty the puzzle has no solution
        for (String value : decimal_values)
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
            input_values[i] = extractCharacters(input_values[i]);

        decimal_values = input_values;
    }

    @Override
    public String findSolution() {
        //Append each value to and output String
        String solution = "";

        //If the puzzle has no solution return nothing
        if (!isSolvable())
            return null;

        //Append each decimal to the solution
        for (String character : decimal_values)
            solution += character + " ";

        return solution;
    }

    //Get decimal values from the input String
    private String extractCharacters(String input)
    {
        //Get current string
        String character_value = input;
        //Remove spaces
        character_value = character_value.replace(" ", "");

        //Loop through each character and get its decimal value
        for (int i = 0; i < accepted_character.length; i++) {
            //Replace character with the proper value
            if (character_value.equals(accepted_character[i]))
                character_value = accepted_character_values[i];
        }

        //Return the extracted String
        return character_value;
    }
}
