package com.isograd.exercise;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // plante vol eau
        System.out.println(IsoContest.calcul(new LinkedList<>(Arrays.asList("feu", "sol", "plante")), new LinkedList<>(Arrays.asList("eau", "plante", "vol"))));
    }
}
