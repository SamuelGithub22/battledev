/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int reserve = Integer.parseInt(sc.nextLine());
        int mise = 0;
        String acheteur = "KO";
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split("\\s");
            int px = Integer.parseInt(split[0]);
            if (px > reserve && px >= mise) {
                mise = px;
                acheteur = split[1];
            }
        }
        System.out.println(acheteur.toUpperCase());
    }
}