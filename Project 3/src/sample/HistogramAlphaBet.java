package sample;

import java.nio.file.Paths;

import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;



public class HistogramAlphaBet {

    private static Scanner read;
    private int totalChar;
    HashMap<Character, Integer> count = new HashMap<Character, Integer> (); //not sorted
     HashMap<Character,Integer> sort = new HashMap<Character, Integer>(); //sorted


    public HistogramAlphaBet()
    {
        totalChar = 0;
        openFile();
        readFile();;
        closeFile();
    }

    public HashMap<Character, Integer>  sortedMap()
    {
        sort=count.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        return sort;

    }


    public void openFile(){
        try {
            read = new Scanner(Paths.get("C:\\\\Users\\\\qxou1\\\\IdeaProjects\\\\project3\\\\src\\\\sample\\\\Alice in Wonderland.txt"));

        }
        catch(Exception a)
        {
            System.out.println("Can't find this file. ");
        }
    }

    public int getTotalChar()
    {
        return totalChar;
    }

    public void readFile()
    {

        for(char i = 'a';i<='z';i++)
        {
            count.put(i,0);

        }



        while(read.hasNext())
        {
            String s = read.nextLine();
            s = s.replaceAll("[^a-zA-Z]", "");
            s = s.toLowerCase();
            totalChar += s.length();

            for(int i =0; i<s.length();i++)
            {
                Character ch = s.charAt(i);

               if(count.containsKey(ch))
                {
                    count.put(ch,count.get(ch)+1);
                }
            }

        }

        sort=count.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

    }

    public void closeFile()
    {
        read.close();
    }

}

