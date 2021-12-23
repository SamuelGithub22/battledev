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
        int M = sc.nextInt();

        if (N != M) {
            System.out.println("ERREUR");
            return;
        }

        while (sc.hasNextInt()) {
            int J = sc.nextInt();
            if (J > N) {
                System.out.println("ERREUR");
                return;
            }
        }
        System.out.println("OK");

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}