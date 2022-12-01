package com.example.puzzlesolver.models.factories;

import android.util.Log;

import com.example.puzzlesolver.models.ArrowGridPuzzle;
import com.example.puzzlesolver.models.ArrowLinePuzzle;
import com.example.puzzlesolver.models.BinaryPuzzle;
import com.example.puzzlesolver.models.CharacterPuzzle;
import com.example.puzzlesolver.models.DecimalPuzzle;
import com.example.puzzlesolver.models.LinePuzzle;
import com.example.puzzlesolver.models.MorsePuzzle;
import com.example.puzzlesolver.models.Puzzle;
import com.example.puzzlesolver.models.RomanPuzzle;
import com.example.puzzlesolver.models.ShapePuzzle;

import java.util.ArrayList;

public class ConverterFactory implements PuzzleFactory {

    //Constants to hold the accepted icons for each puzzle
    private final String accepted_binary[] = {"700", "701"}; //ID 0
    private final String accepted_morse[] = {"100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134"}; //ID 1
    private final String accepted_roman[] = {"400", "401", "402", "403", "404", "405", "406", "407", "408"}; //ID 2
    private final String accepted_shapes[] = {"200", "201", "202", "203", "204", "205", "206", "207", "208"}; //ID 3
    private final String accepted_arrow_line[] = {"0", "1", "2", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 4
    private final String accepted_arrow_grid[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; //ID 5
    private final String accepted_decimal[] = {"300", "301", "302", "303", "304", "305", "306", "307", "308", "309", "310", "311", "312", "313", "314", "315", "316", "317", "318", "319", "320", "321", "322", "323", "324", "325"}; //ID 6
    private final String accepted_character[] = {"500", "501", "502", "503", "504", "505", "506", "507", "508"};//ID 7
    private final String accepted_line[] = {"800", "801", "802", "803", "804", "805", "806", "807", "808"}; //ID 8

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
        else if (findType(input) == 3)
            puzzle = new ShapePuzzle();
        else if (findType(input) == 4)
            puzzle = new ArrowLinePuzzle();
        else if (findType(input) == 5)
            puzzle = new ArrowGridPuzzle();
        else if (findType(input) == 6)
            puzzle = new DecimalPuzzle();
        else if (findType(input) == 7)
            puzzle = new CharacterPuzzle();
        else if (findType(input) == 8)
            puzzle = new LinePuzzle();

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
        else if (findValue(accepted_line, inputs))
            id = 8; //Character puzzle
        else
            id = 9; //Puzzle not valid

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
