/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        //String  line;
        Scanner sc = new Scanner(System.in);
        //init lié à la ligne 1
        int place = Integer.parseInt(sc.nextLine()) ;

        while(sc.hasNextLine()) {
            String line[] = sc.nextLine().split( " ");
            place = place -(Integer.parseInt(line[0]) )+ (Integer.parseInt(line[1]));
        }

        String message = "KO";
            // determine la place avec les deux arguments pour les autre lignes
        if (place <= 100) {
           message = "1000";
        }else if (place <= 10000){
            message = "100";
        }

        System.out.println(message);
        //traitement la place pour determiner le montant


        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}