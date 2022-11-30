package com.example.puzzlesolver.models;

public class MorsePuzzle implements Puzzle {

    //Array of strings to hold the morse values
    //Starting at A ending with Z
    //                                     A      B       C      D     E      F      G       H      I      J      K       L     M     N      O       P       Q      R      S     T     U      V       W      X        Y       Z
    private final String morse_keys[] = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    private String accepted_morse[];
    private String morse_values[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String character_values[];

    @Override
    public boolean isSolvable() {
        return false;
    }

    @Override
    public void setProblem(String input) {
        //Split the input string on the "-" delimiter
        String input_values[] = input.split("-");

        //Loop through the entire list of characters, not special values needed
        //Convert values from ids to the correct character
        for (int i = 0; i < input_values.length; i++)
            input_values[i] = extractMorse(input_values[i]);

        character_values = input_values;
    }

    @Override
    public String findSolution() {
        return null;
    }

    //Get arrow values from the input String
    private String extractMorse(String input)
    {
        //Get current string
        String morse_value = input;
        //Remove spaces
        morse_value = morse_value.replace(" ", "");

        //Replace icon ids with their corresponding values
        //for (int j = 0; j < morse_keys.length; j++)
            //morse_value = morse_value.replace(accepted_arrow_line[j], accepted_arrow_line_values[j]);

        //Replace the string at the given index
        return morse_value;
    }
}
