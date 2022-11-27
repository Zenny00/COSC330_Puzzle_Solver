package com.example.puzzlesolver.models;



public class LightsOutPuzzle implements Puzzle{
    private boolean[] puzzleVector;
    private int M, N;

    @Override
    public boolean isSolvable() {
        if (M < 0 || M > 6)
            return false;
        if (N < 0 || N > 6)
            return false;
        return true;
    }
    @Override
    public void setProblem(String problem) {
        M = Integer.parseInt(String.valueOf(problem.charAt(0)));
        N = Integer.parseInt(String.valueOf(problem.charAt(1)));

        if (isSolvable()) {
            puzzleVector = new boolean[M * N];
            for (int i = 2; i < problem.length(); i++)
                if (problem.charAt(i) == '1') {
                    puzzleVector[i-2] = true;
                } else if (problem.charAt(i) == '0') {
                    puzzleVector[i-2] = false;
                }
        }
    }
    @Override
    public String findSolution() {
        //boolean[][] toggle = makeToggleMatrix();
        //boolean[] solutionVector = solzePuzzle(toggle, puzzleVector);
        //return makeCleanString(solutionVector);
        return "PRESS THE FOLLOWING BUTTONS\n1 1 1\n0 1 0\n1 1 1";
    }

    // HELPER FUNCTIONS
    public void setDimensions(int rows, int cols) {
        M = rows;
        N = cols;
    }
}
