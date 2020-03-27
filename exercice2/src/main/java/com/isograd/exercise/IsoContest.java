/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Objects;
import java.util.Scanner;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        System.err.println("----------------");
        String line;
        Scanner sc = new Scanner(System.in);
        System.err.println(sc.nextLine());
        int max_count = 1;
        String previous = sc.nextLine().trim();
        System.err.println(previous);
        int count = 1;
        while (sc.hasNextLine()) {
            String number = sc.nextLine().trim();
            System.err.println(number);
            if (Objects.equals(previous, number)) {
                count++;
            } else if (count > max_count) {
                max_count = count;
                previous = number;
                count = 1;
            } else {
                previous = number;
                count = 1;
            }
            //System.err.println(number + " -- " + max_count + " -- " + count);
            /* Lisez les données et effectuez votre traitement */
        }
        if (count > max_count) {
            max_count = count;
        }
        System.out.println(max_count);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}