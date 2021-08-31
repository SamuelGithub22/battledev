/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IsoContest {

    public static void main(String[] argv) throws Exception {
        System.err.println("---");
        Scanner sc = new Scanner(System.in);
        String line = "";
        int l = -1;
        int max = 0;
        int[] ok = new int[10];

        for (int i = 0; i < 20; i++) {
            line = sc.nextLine();

            int current = -1;
            int nb = 0;
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) == '.') {
                    if (ok[j] != 1) {
                        current = j;
                    }
                    nb++;
                } else {
                    ok[j] = 1;
                }
            }

            if (current == -1) {
                break;
            } else if (l == current && nb == 1) {
                max++;
            } else if (l == -1 && nb == 1) {
                l = current;
                max = 1;
            } else if (l != -1 && line.charAt(l) == '#' && max >= 4) {
                break;
            } else if (nb > 1 && l != -1) {
                max = 0;
                l = -1;
                break;
            }

        }
//        System.err.println(lst);
        if (max >= 4) {
            System.out.printf("BOOM %s%n", l + 1);
        } else {
            System.out.println("NOPE");
        }
//        System.out.println("NOPE");
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

}