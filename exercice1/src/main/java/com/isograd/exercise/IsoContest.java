/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Scanner;

public class IsoContest {

    public static void main(String[] argv) throws Exception {
        Scanner sc = new Scanner(System.in);
        int D = Integer.parseInt(sc.nextLine());
        int L = Integer.parseInt(sc.nextLine());
        System.out.println(D + L * 5);
    }

}