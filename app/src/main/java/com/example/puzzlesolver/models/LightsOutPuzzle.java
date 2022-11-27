package com.example.puzzlesolver.models;

public class LightsOutPuzzle implements Puzzle{
    private boolean[][] puzzleMatrix;
    private int M, N;

    @Override
    public boolean isSolvable() {
        // TODO: implement a checking system
        return true;
    }
    @Override
    public void setProblem(String input) {
        // TODO: need to decide how to read in information
        M = 3; // placeholder values
        N = 3;

        // TODO: should set puzzle matrix with true/false
        puzzleMatrix = new boolean[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++) {
                puzzleMatrix[i][j] = false;
            }

    }
    @Override
    public String findSolution() {
        //boolean[][] toggle = makeToggleMatrix();
        //boolean[] puzzleVector = linearizePuzzle(puzzleMatrix);
        //boolean[] solutionVector = solzePuzzle(toggle, puzzleVector);
        //return makeCleanString(solutionVector);
        return null;
    }

    // HELPER FUNCTIONS
}
