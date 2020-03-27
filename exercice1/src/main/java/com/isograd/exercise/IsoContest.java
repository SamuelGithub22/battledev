/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);
        Map<String, AtomicInteger> map = new HashMap<>();
       Object key;
       int nb = Integer.parseInt( sc.nextLine());
        while(sc.hasNextLine()) {
            map.computeIfAbsent(sc.nextLine(), s -> new AtomicInteger()).incrementAndGet();
            /* Lisez les données et effectuez votre traitement */
        }
        System.err.println(map);
        String out = map.entrySet().stream().sorted(Comparator.comparing(e -> -e.getValue().get()))
                .limit(2).map(Map.Entry::getKey).collect(Collectors.joining(" "));
        System.out.println(out);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}