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
        sc.nextLine();
        int min = Integer.MAX_VALUE;
        String perdant = null;
        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            String nom = line.split("\\s")[0];
            int taille = Integer.parseInt(line.split(" ")[1]);
            System.err.println(line);
            System.err.println(nom);
            if (taille < min) {
                min = taille;
                perdant = nom;
            }
            /* Lisez les données et effectuez votre traitement */
        }
        System.out.println(perdant);
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}