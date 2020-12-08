package com.javarush.games.snake;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        System.out.println("Hi");

        List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println();

        list.remove(list.size()-1);

        for (String s : list) {
            System.out.println(s);
        }
    }
}
