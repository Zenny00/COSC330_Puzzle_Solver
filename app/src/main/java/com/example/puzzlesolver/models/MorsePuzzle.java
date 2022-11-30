package com.example.puzzlesolver.models;

public class MorsePuzzle implements Puzzle {

    //Array of strings to hold the morse values
    //Starting at A ending with Z
    //                                     A      B       C      D     E      F      G       H      I      J      K       L     M     N      O       P       Q      R      S     T     U      V       W      X        Y       Z
    private final String morse_keys[] = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
    private String morse_values[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    @Override
    public boolean isSolvable() {
        return false;
    }

    @Override
    public void setProblem(String input) {

    }

    @Override
    public String findSolution() {
        return null;
    }
}
