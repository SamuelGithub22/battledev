/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Arrays;
import java.util.Scanner;

public class IsoContest {

    public static void main(String[] argv) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String line = sc.nextLine();

        int[] tot = new int[26];
        for (int i = 0; i < n; i++) {
            char s = line.charAt(i);
            tot[s - 'A']++;
        }
        for (int i = 0; i < tot.length; i++) {
            tot[i] /= 2;
        }

        int count = 0;

        int[] orb = new int[26];
        for (int j = 0; j < n / 2; j++) {
            char s = line.charAt(j);
            orb[s - 'A']++;
        }

        for (int i = 0; i < n / 2; i++) {
            char d = line.charAt(i);
            char f = line.charAt(i + n / 2);

            orb[d - 'A']--;
            orb[f - 'A']++;

            if (Arrays.equals(tot, orb)) {
                count++;
            }
        }

        System.out.println(count * 2);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

}