/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);
        List<Integer> lst = new ArrayList<>();
        while(sc.hasNextLine()) {
            lst.add(Integer.parseInt(sc.nextLine()));
            /* Lisez les données et effectuez votre traitement */
        }
        int min = lst.stream().min(Integer::compareTo).get();
        int cpt = 0;
        for (Integer integer : lst) {
            cpt += integer- min;
        }
        System.out.println(cpt);
    }
}