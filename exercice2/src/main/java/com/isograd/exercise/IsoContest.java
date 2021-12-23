/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);

        line = sc.nextLine();
        /* Lisez les données et effectuez votre traitement */
        String[] ligneJours = line.split(" ");
        int N = parseInt(ligneJours[0]);
        int M = parseInt(ligneJours[1]);

        line = sc.nextLine();
        ligneJours = line.split(" ");
        String resultat = "OK";

        for (String jour : ligneJours) {
            if (N != M || parseInt(jour) > N) {
                resultat = "ERREUR";
                break;
            }

        }
        System.out.println(resultat);



        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}