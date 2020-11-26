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
        // algo trouvé sur :
        // https://www.geeksforgeeks.org/xor-of-a-subarray-range-of-elements-set-2/?ref=rp
        int[] octets = Arrays.stream(split).mapToInt(Integer::parseInt).toArray();
        for (int i = 1; i < octets.length; i++) {
            octets[i] = octets[i] ^ octets[i - 1];
        }
        int[] result = new int[256];
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            split = line.split(" ");
            int L = Integer.parseInt(split[0]);
            int R = Integer.parseInt(split[1]);
            int x;
            if (L == 0) {
                x = octets[R];
            } else {
                x = octets[L - 1] ^ octets[R];
            }
            result[x]++;
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        System.out.println(Arrays.stream(result).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }
}