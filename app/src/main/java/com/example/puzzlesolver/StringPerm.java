package com.example.puzzlesolver;

public class StringPerm {

    private int count = -1;
    private int numOfPerms;
    private String word;
    private int stringLength;
    private int[] permNums;
    private String[] permWords;
    private int permWordsCt = 0;

    public StringPerm(String word)
    {
        this.word = word;
        stringLength = word.length();
        numOfPerms = numberOfPermutations(stringLength);
        permNums = new int[stringLength];
        permWords = new String[numOfPerms];
    }

    public String[] makePermutations(int characterPos)
    {
        count++;
        permNums[characterPos] = count;

        if (count == stringLength) {
            append();
        }
        else
        {
            for (int i = 0; i < stringLength; i++)
            {
                if (permNums[i] == 0)
                {
                    makePermutations(i);
                }
            }
        }
        count--;
        permNums[characterPos] = 0;
        return permWords;
    }

    public void append() {
        String tmpWord = "";
        for (int i = 0; i < stringLength; i++)
        {
            tmpWord = tmpWord + word.charAt(permNums[i] - 1);
        }
        
        if (permWordsCt < numOfPerms)
        {
            permWords[permWordsCt] = tmpWord;
            permWordsCt++;
        }
    }

    public void printPermutations()
    {
        for (int i = 0; i < numOfPerms; i++)
        {
            System.out.println(permWords[i]);
        }
    }

    public int numberOfPermutations(int length)
    {
        int total = length;
        for (int i = length - 1; i > 0; i--)
        {
            total *= i;
        }
        return total;
    }
}
