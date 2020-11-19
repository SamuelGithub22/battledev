/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.time.LocalTime;
import java.util.Scanner;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        Scanner sc = new Scanner(System.in);
        int nb = Integer.parseInt(sc.nextLine());
        boolean[][] calendar = new boolean[5][600];
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            // jour hh:mm-hh:mm
            String[] splitJ = line.split("[ \\:\\-]");
            int jour = Integer.parseInt(splitJ[0]) - 1;
            int h1 = Integer.parseInt(splitJ[1]) - 8;
            int m1 = Integer.parseInt(splitJ[2]);
            int h2 = Integer.parseInt(splitJ[3]) - 8;
            int m2 = Integer.parseInt(splitJ[4]);

            for (int i = h1 * 60 + m1; i <= h2 * 60 + m2; i++) {
                calendar[jour][i] = true;
            }
            /* Lisez les données et effectuez votre traitement */
        }
        //print(calendar);


        for (int jour = 0; jour < 5; jour++) {
            int cpt = 0;
            for (int i = 0; i < 600; i++) {
                if (calendar[jour][i]) {
                    cpt = 0;
                    System.err.println(i + " " + cpt);
                } else if (++cpt == 60) {
                    System.err.println(i + " " + cpt);
                    // jour hh:mm-hh:mm
                    System.out.println(String.format("%d %s-%s",
                            (jour + 1),
                            heure(i - 59),
                            heure(i)));
                    return;
                } else {
                    System.err.println(i + " " + cpt);
                }
            }
        }
        System.out.println("ERR");
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    private static String heure(int i) {
        return String.format("%02d:%02d", i / 60 + 8, i % 60);
    }

    private static void print(boolean[][] calendar) {
        for (int i = 0; i < 600; i++) {
            for (int jour = 0; jour < 5; jour++) {
                System.err.print(calendar[jour][i] ? "X" : "_");
            }
            System.err.println();
        }
    }
}