/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);
        int nb_carton= Integer.parseInt(sc.nextLine());
        int poids=0;
        int nb=1;
        while(sc.hasNextLine()) {
            int poids_carton = Integer.parseInt(sc.nextLine());
           if ( poids + poids_carton <= 100){
               poids += poids_carton;
           }
           else{
               nb++;
               poids = poids_carton;
           }



            /* Lisez les données et effectuez votre traitement */
        }
        System.out.println(nb);

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }
}