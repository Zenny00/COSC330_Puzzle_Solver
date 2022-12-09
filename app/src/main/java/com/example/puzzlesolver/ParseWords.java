package com.example.puzzlesolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParseWords {

    static List<String> inMemoryList;
    static Object[] objArray;
    private static InputStream stream;
    private String printer = "";
    private boolean isSolved = false;

    public ParseWords()
    {

    }

    public ParseWords(String scrambled) {
        inMemoryList = new ArrayList<String>();
        getDictionaryPath();
        OpenAndParseFile();
        StringPerm perm = new StringPerm(scrambled);
        String[] list = perm.makePermutations(0);
        List<String> found = this.permuteAndFindWord(list);
        this.display(found);
    }

    public void OpenAndParseFile() {
        BufferedReader bis = null;

        try {
	        bis = new BufferedReader(new InputStreamReader(stream));
            String line;
            while ((line = bis.readLine()) != null) {
                inMemoryList.add(line);
            }
            objArray = inMemoryList.toArray();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void printWords() {
        try {
            PrintWriter writer = new PrintWriter(new File("/tmp/words.txt"));

            int len = inMemoryList.size();
            for (int i = 0; i < len; i++) {
                String string = inMemoryList.get(i);
                writer.println("\"" + string + "\"" + ",");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ParseWords.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> permuteAndFindWord(String[] input) {

        String queryStr = input[0];
        int lenInput = input.length;
        List<String> found = new ArrayList<String>();
        if (fullCounter(queryStr, '_') > 0)
        {
            if (fullCounter(queryStr, '_') >= emptyCounter(queryStr, '_'))
            {
                queryStr = queryStr.replaceAll("\\_", "\\\\w");
                found = query(queryStr);
            }
        }
        for (int i = 0; i < lenInput; i++) {
            String word = input[i];

            int index = Arrays.binarySearch(objArray, word);
            if (index >= 0) {
                found.add((String) objArray[index]);
            }
            if(found != null && !found.isEmpty())
                isSolved = true;
        }
        return found;
    }

    public void display(List<String> list)
    {
        if (list != null && !list.isEmpty()) {
            Set<String> hashSet = new LinkedHashSet(list);
            ArrayList<String> removeDuplicates = new ArrayList(hashSet);

            System.out.println(removeDuplicates);
            printer = String.valueOf(removeDuplicates);
        } else {
            printer = "No solutions found";
        }
    }

    public void clearPrinter()
    {
        printer = "Nothing Entered";
    }

    public String getPrinter()
    {
        if(printer.length() > 0)
        {
            return printer;
        }
        return "Unsolvable";
    }

    public static void getDictionaryPath()
    {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader == null)
            {
                contextClassLoader = ParseWords.class.getClassLoader();
            }
            InputStream is = contextClassLoader.getResourceAsStream("res/raw/dictionary");
            if (is != null)
            {
		        stream = is;
            }
    }
    public boolean getSolved()
    {
        return isSolved;
    }
    public int emptyCounter(String x, char y)
    {
        int count = 0;
        for(int i = 0; i < x.length(); i++)
        {
            if(y == x.charAt(i))
            {
                count++;
            }
        }
        return count;
    }

    public static List<String> query(String queryStr) {
        List<String> list = new ArrayList<String>();
        for (String str : inMemoryList) {
            if (str.matches(queryStr))
                list.add(str);
        }
        if (list.isEmpty())
            return null;
        else
            return list;
    }

    public int fullCounter(String x, char y)
    {
        int count = 0;
        for(int i = 0; i < x.length(); i++)
        {
            if(y != x.charAt(i))
            {
                count++;
            }
        }
        return count;
    }
}

