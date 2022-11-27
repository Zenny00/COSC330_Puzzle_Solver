package com.example.puzzlesolver.models.factories;

import android.util.Log;

import com.example.puzzlesolver.models.BinaryPuzzle;
import com.example.puzzlesolver.models.Puzzle;

public class ConverterFactory implements PuzzleFactory {

    //Constants to hold the accepted icons for each puzzle
    private final String accepted_binary[] = {"0", "5", "6"}; //ID 0
    private final String accepted_morse[] = {"0", "18", "19"}; //ID 1
    private final String accepted_roman[] = {"0", "20", "21", "22", "23", "24", "25", "26", "27", "28"}; //ID 2
    private final String accepted_shapes[] = {"0", "15", "16", "17"}; //ID 3
    private final String accepted_arrow_line[] = {"0", "1", "2", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 4
    private final String accepted_arrow_grid[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 5
    private final String accepted_decimal[] = {"0", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 6
    private final String accepted_character[] = {"0", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54"}; //ID 7

    @Override
    public Puzzle createPuzzle(String input) {

        //Check the puzzle type and return the appropriate puzzle
        if (findType(input) == 0)
            return new BinaryPuzzle();

        return null;
    }

    @Override
    public int findType(String input) {
        //Split the input string on spaces
        String input_values[] = input.split(" ");
        for (String value : input_values)
            Log.d("puzzle values", value + "\n");

        //Puzzle id variable
        int id;

        //Check which puzzle the pattern belongs to
        if (findValue(accepted_binary, input_values))
            id = 0; //Binary puzzle
        else if (findValue(accepted_morse, input_values))
            id = 1; //Morse puzzle
        else if (findValue(accepted_roman, input_values))
            id = 2; //Roman numeral puzzle
        else if (findValue(accepted_shapes, input_values))
            id = 3; //Shape puzzle
        else if (findValue(accepted_arrow_line, input_values))
            id = 4; //Arrow line puzzle
        else if (findValue(accepted_arrow_grid, input_values))
            id = 5; //Arrow grid puzzle
        else if (findValue(accepted_decimal, input_values))
            id = 6; //Decimal puzzle
        else if (findValue(accepted_character, input_values))
            id = 7; //Character puzzle
        else
            id = 8; //Puzzle not valid

        Log.d("Puzzle ID", String.valueOf(id));

        //Return the puzzle id
        return id;
    }

    //Helper function to check if values are contained within accepted array
    private boolean findValue(String accepted[], String input[])
    {
        //Big O(n^2)
        //Check each element of the input array
        for (String curr : input)
        {
            boolean notFound = true;
            //Check to see if the value exists in the accepted array
            for (String value : accepted)
                if (value.equals(curr))
                    notFound = false;

            Log.d("Boolean", String.valueOf(notFound));

            //If the value was not found, return false
            if (notFound)
                return  false;
        }

        //If we never returned false, the value was found
        return true;
    }
}
