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
        int reservoir = Integer.parseInt(sc.nextLine());
        int consommation = Integer.parseInt(sc.nextLine());

        int trajet = 0;
        while (sc.hasNextLine()) {
            int etape = Integer.parseInt(sc.nextLine());
            double carburant = (etape - trajet) * consommation / 100.;
            if (carburant > reservoir) {
                System.out.println("KO");
                return;
            }
            trajet += etape - trajet;
        }
        System.out.println("OK");
    }
}