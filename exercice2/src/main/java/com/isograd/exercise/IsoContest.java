/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class IsoContest {

    public static void main(String[] argv) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Map<String, AtomicInteger> boutons = new HashMap<>();
        while (sc.hasNextLine()) {
            String b = sc.nextLine();
            boutons.computeIfAbsent(b, s -> new AtomicInteger()).incrementAndGet();
            /* Lisez les données et effectuez votre traitement */
        }
        boutons.entrySet().stream()
                .filter(k -> k.getValue().intValue() == 2)
                .findFirst()
                .map(Map.Entry::getKey)
                .ifPresent(System.out::println);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

}