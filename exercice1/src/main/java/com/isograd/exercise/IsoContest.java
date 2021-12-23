/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Scanner;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = 0;

        while (sc.hasNextInt()) {
            int H = sc.nextInt();
            /* Lisez les données et effectuez votre traitement */
            if (H <= 250 && H > M) {
                M = H;
            }
        }

        System.out.println(M);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}