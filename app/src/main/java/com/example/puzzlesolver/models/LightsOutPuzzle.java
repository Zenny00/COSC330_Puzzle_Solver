/*  Implementation Logic is from the Archive of Interesting Code
    Author: Keith Schwarz (htiel@cs.standford.edu)

    This program uses linear algebra and a matrix representation of a
    lights out puzzle in order to create a solution that will leave all
    the lights on. 
    
    Last Updated: 11/30/2022 by Justin Conklin */

package com.example.puzzlesolver.models;

import java.util.Arrays;

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
            invertPuzzle(puzzleVector);
        }
    }
    @Override
    public String findSolution() {
        boolean[][] toggle = makeToggleMatrix();
        boolean[] solutionVector = solvePuzzle(toggle, puzzleVector);
        if (solutionVector.length == 1)
            return "No solutions found for light configuration";
        else
            return makeCleanString(solutionVector);
    }

    // HELPER FUNCTIONS
    public String makeCleanString(boolean[] solution) {
        String header = "PRESS THE FOLLOWING LIGHTS\n";
        String solutionSet = "";

        for (int i = 0; i < M*N; i++) {
            if (i % (N) == 0 && i != 0)
                solutionSet += "\n";

            if (solution[i])
                solutionSet += "1 ";
            else
                solutionSet += "0 ";
        }
        return header + solutionSet;
    }

    public boolean[] solvePuzzle(boolean[][] toggle, boolean[] puzzle) {
        performGaussianElimination(toggle, puzzle);
        return backSubstitute(toggle, puzzle);
    }

    public int rowMajorIndex(int i, int j) { return (i * N) + j;}

    public boolean[][] makeToggleMatrix() {
        boolean[][] result = new boolean[M*N][M*N];
        for (int i = 0; i < M*N; i++)
            for (int j = 0; j < N*M; j++)
                result[i][j] = false;

        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++) {
                int column = rowMajorIndex(i, j);
                result[column][rowMajorIndex(i, j)] = true;

                for (int di = -1; di <= 1; di++)
                    for (int dj = -1; dj <= 1; dj++)
                        if ((di == 0) == (dj == 0))
                            continue;
                        else if ((i + di < M) && (j + dj < N) && (i + di >= 0) && (j + dj >= 0))
                            result[column][rowMajorIndex(i+di,j+dj)] = true;
            }
        return result;
    }

    public int findPivot(boolean[][] matrix, int startRow, int pivotColumn) {
        for (int row = startRow; row < (M*N); row++)
            if (matrix[row][pivotColumn])
                return row;
        return -1;
    }

    public void swapRows(boolean[] r1, boolean[] r2) {
        final int size = r1.length;

        for (int i = 0; i < size; i++) {
            boolean temp = r1[i];
            r1[i] = r2[i];
            r2[i] = temp;
        }
    }

    public void swap(boolean[] r1, int pos1, boolean[] r2, int pos2) {
        boolean temp = r1[pos1];
        r1[pos1] = r2[pos2];
        r2[pos2] = temp;
    }

    public void transform(boolean[] r1, boolean[] r2) {
        final int size = r1.length;
        for (int i = 0; i < size; i++)
            r2[i] = (r2[i] != r1[i]);
    }

    /*  A normal lights out puzzle seeks to turn off all the lights
        The puzzle we want to solve is one where we turn on all the
        lights. To do this we invert the puzzle because the solution
        that turns off all the lights of the inverse puzzle would be
        the same solution that turns on all the lights of the original
        puzzle. */
    public void invertPuzzle(boolean[] puzzle) {
        for (int i = 0; i < M*N; i++)
            puzzle[i] = !puzzle[i];
    }

    public void performGaussianElimination(boolean[][] toggle, boolean[] puzzle) {
        int nextFreeRow = 0;
        for (int col = 0; col < (M*N); col++) {
            int pivotRow = findPivot(toggle, nextFreeRow, col);
            if (pivotRow == -1)
                continue;
            swapRows(toggle[pivotRow], toggle[nextFreeRow]);
            swap(puzzle, pivotRow, puzzle, nextFreeRow);

            for (int row = pivotRow + 1; row < (M*N); row++)
                if (toggle[row][col]) {
                    transform(toggle[nextFreeRow], toggle[row]);
                    puzzle[row] ^= puzzle[nextFreeRow];
                }
            nextFreeRow++;
        }
    }

    public boolean[] backSubstitute(boolean[][] toggle, boolean[] puzzle) {
        boolean[] result = new boolean[M*N];
        for (int i = 0; i < (M*N); i++)
            result[i] = false;

        for (int row = (M*N); row-- != 0;) {
            int pivot = -1;

            for (int col = 0; col < (M*N); col++) {
                if (toggle[row][col]) {
                    pivot = col;
                    break;
                }
            }
            if (pivot == -1) {
                if (puzzle[row])
                    return new boolean[1];
            } else {
                result[row] = puzzle[row];
                for (int col = pivot + 1; col < (M*N); col++)
                    result[row] = (result[row] != (toggle[row][col] & result[col]));
            }
        }
        return result;
    }
}
