/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();
        String[] split = line.split(" ");
        int N = Integer.parseInt(split[0]);
        int M = Integer.parseInt(split[1]);
        line = sc.nextLine();
        split = line.split(" ");
        int[] octets = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        int[] result = new int[256];
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            split = line.split(" ");
            int L = Integer.parseInt(split[0]);
            int R = Integer.parseInt(split[1]);
            int x = octets[L];
            for (int i = L + 1; i <= R; i++) {
                x = x ^ octets[i];
            }
            result[x] ++;
            /* Lisez les données et effectuez votre traitement */
        }
        System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}