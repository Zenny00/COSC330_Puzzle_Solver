//Name: Joshua Comfort
//Date: 11/22/2022-12/10/2022
//Description: Roman numeral puzzle, converts Roman numerals to decimal

package com.example.puzzlesolver.models;

import android.util.Log;

public class RomanPuzzle extends ConverterPuzzle {
    //Call the superclass constructor to build a new converter puzzle
    public RomanPuzzle()
    {
        super(new String[]{"400", "401", "402", "403", "404", "405", "406", "407", "408"}, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
    }
}
