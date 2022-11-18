package com.example.puzzlesolver.models;

public interface Puzzle {
    public boolean isSolvable();
    public void setProblem(String input);
    public String findSolution();
}
