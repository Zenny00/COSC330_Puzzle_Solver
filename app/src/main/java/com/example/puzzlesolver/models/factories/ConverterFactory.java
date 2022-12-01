package com.example.puzzlesolver.models.factories;

import android.util.Log;

import com.example.puzzlesolver.models.ArrowGridPuzzle;
import com.example.puzzlesolver.models.ArrowLinePuzzle;
import com.example.puzzlesolver.models.BinaryPuzzle;
import com.example.puzzlesolver.models.MorsePuzzle;
import com.example.puzzlesolver.models.Puzzle;
import com.example.puzzlesolver.models.RomanPuzzle;

import java.util.ArrayList;

public class ConverterFactory implements PuzzleFactory {

    //Constants to hold the accepted icons for each puzzle
    private final String accepted_binary[] = {"700", "701"}; //ID 0
    private final String accepted_morse[] = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134"}; //ID 1
    private final String accepted_roman[] = {"400", "401", "402", "403", "404", "405", "406", "407", "408"}; //ID 2
    private final String accepted_shapes[] = {"0", "15", "16", "17"}; //ID 3
    private final String accepted_arrow_line[] = {"0", "1", "2", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 4
    private final String accepted_arrow_grid[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 5
    private final String accepted_decimal[] = {"0", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 6
    private final String accepted_character[] = {"0", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54"}; //ID 7

    @Override
    public Puzzle createPuzzle(String input) {
        Puzzle puzzle = null;

        //Check the puzzle type and return the appropriate puzzle
        if (findType(input) == 0)
            puzzle = new BinaryPuzzle();
        else if (findType(input) == 1)
            puzzle = new MorsePuzzle();
        else if (findType(input) == 2)
            puzzle = new RomanPuzzle();
        else if (findType(input) == 4)
            puzzle = new ArrowLinePuzzle();
        else if (findType(input) == 5)
            puzzle = new ArrowGridPuzzle();

        return puzzle;
    }

    @Override
    public int findType(String input) {
        //Split on delimiter
        String input_lines[] = input.split("-");

        //ArrayList to hold characters
        ArrayList<String> inputs = new ArrayList<String>();

        //Go through each of the input sections
        for (String line : input_lines)
        {
            //Split on spaces
            String characters[] = line.split(" ");
            for (String character : characters)
                inputs.add(character);
        }

        //Puzzle id variable
        int id;

        //Check which puzzle the pattern belongs to
        if (findValue(accepted_binary, inputs))
            id = 0; //Binary puzzle
        else if (findValue(accepted_morse, inputs))
            id = 1; //Morse puzzle
        else if (findValue(accepted_roman, inputs))
            id = 2; //Roman numeral puzzle
        else if (findValue(accepted_shapes, inputs))
            id = 3; //Shape puzzle
        else if (findValue(accepted_arrow_line, inputs))
            id = 4; //Arrow line puzzle
        else if (findValue(accepted_arrow_grid, inputs))
            id = 5; //Arrow grid puzzle
        else if (findValue(accepted_decimal, inputs))
            id = 6; //Decimal puzzle
        else if (findValue(accepted_character, inputs))
            id = 7; //Character puzzle
        else
            id = 8; //Puzzle not valid

        Log.d("Puzzle ID", String.valueOf(id));

        //Return the puzzle id
        return id;
    }

    //Helper function to check if values are contained within accepted array
    private boolean findValue(String accepted[], ArrayList<String> input)
    {
        //Check if the array is empty
        if (input.isEmpty())
            return false;

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
            if (notFound || curr.equals(""))
                return  false;
        }

        //If we never returned false, the value was found
        return true;
    }
}
