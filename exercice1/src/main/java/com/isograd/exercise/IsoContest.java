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
        int i = 0;
        while(sc.hasNextLine()) {
            line = sc.nextLine();
            if (line.matches(".*\\d\\d\\d\\d\\d")) {
                i++;
            }
            /* Lisez les données et effectuez votre traitement */
        }
        System.out.println(i);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}