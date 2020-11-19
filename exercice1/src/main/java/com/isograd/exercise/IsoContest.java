/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        List<String> colors = new ArrayList<>();
        while (sc.hasNextLine()) {
            colors.add(sc.nextLine());
            /* Lisez les données et effectuez votre traitement */
        }

        Comparator<Map.Entry<String, Long>> sort = Map.Entry.comparingByValue();

        String str = colors.stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                ).entrySet()
                .stream()
                .sorted(sort.reversed())
                .limit(2)
                .peek(System.err::println)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(" "));
        System.out.println(str);

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}