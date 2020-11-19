/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Scanner;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        int line;
        Scanner sc = new Scanner(System.in);

        int nb = Integer.parseInt(sc.nextLine());

        int i = 0;

        int max = 1;
        int tempMax = 1;
        int carte = Integer.parseInt(sc.nextLine());
        System.err.println(carte);
        while (sc.hasNextLine() && i++ < nb) {
            line = Integer.parseInt(sc.nextLine());
            System.err.println(line);
            if (line == carte) {
                tempMax++;
            } else {
                tempMax = 1;
            }
            carte = line;
            if (tempMax > max) max = tempMax;
            /* Lisez les données et effectuez votre traitement */
        }

        System.out.println(max);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/


    }
}