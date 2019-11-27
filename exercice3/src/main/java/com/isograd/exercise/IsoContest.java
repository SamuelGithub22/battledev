/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.*;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int last = sc.nextInt();
        int cpt = 0;
        while (sc.hasNextInt()) {
            int i = sc.nextInt();
            if (last * 2 < n && i * 2 > n) {
                cpt++;
            } else if (last * 2 > n && i * 2 < n) {
                cpt++;
            } else if (i == last && i * 2 == n) {
                System.out.println("INF");
                return;
            } else if (i * 2 == n) {
                cpt++;
            }
            last = i;
        }
        System.out.println(cpt);
    }
}