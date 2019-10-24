
package com.isograd.exercise;
import java.util.*;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);
        int dim = Integer.parseInt(sc.nextLine());
        String map = "";
        while(sc.hasNextLine()) {
            map += sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
        }
        StringBuilder trajet = new StringBuilder();
        parcours(map, dim, trajet, 'o');
        for (int i = 1; i < dim; i++) {
            trajet.append('<');
            trajet.append('^');
        }
        parcours(map, dim, trajet, '*');

        System.out.println(trajet);

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    private static void  parcours(String map, int dim, StringBuilder trajet, char item) {
        for (int i = 0; i < map.length(); i++) {
            char c = map.charAt(i);
            if (c == item) {
                trajet.append('x');
            }
            if ((i+1) % dim > 0) {
                trajet.append('>');
            } else if (i+1 != map.length()) {
                trajet.append('v');
//                trajet.append('\n');
                for (int j = 1; j < dim; j++) {
                    trajet.append('<');
                }
            }
        }
    }
}