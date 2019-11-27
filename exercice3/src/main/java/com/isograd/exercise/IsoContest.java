/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();
        int[] r = new int[m];
        Set[] temps = new Set[2500];
        for (int i = 0; i < temps.length; i++) {
            temps[i] = IntStream.range(0, n).boxed().collect(Collectors.toSet());
        }
        int q = 0;
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            int debut = Integer.parseInt(line.split(" ")[0]);
            int fin = Integer.parseInt(line.split(" ")[1]);
            Set<Integer> set = IntStream.range(0, n).boxed().collect(Collectors.toSet());

            for (int i = debut; i < fin; i++) {
                set.retainAll(temps[i]);
            }
            if (set.isEmpty()) {
                System.out.println("pas possible");
                return;
            }
            int c = set.iterator().next();
            r[q++] = c;

            for (int i = debut; i < fin; i++) {
                temps[i].remove(c);
            }
            /* Lisez les données et effectuez votre traitement */
        }
        System.out.println(Arrays.stream(r).map(i -> i + 1).boxed().map(String::valueOf).collect(Collectors.joining(" ")));
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}