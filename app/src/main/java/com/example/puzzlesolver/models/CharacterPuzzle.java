//Name: Joshua Comfort
//Date: 11/22/2022-12/10/2022
//Description: Character puzzle, converts alphabetic characters to decimal

package com.example.puzzlesolver.models;

public class CharacterPuzzle extends ConverterPuzzle {
    //Call the superclass constructor to build a new converter puzzle
    public CharacterPuzzle()
    {
        super(new String[]{"500", "501", "502", "503", "504", "505", "506", "507", "508"}, new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"});
    }
}
