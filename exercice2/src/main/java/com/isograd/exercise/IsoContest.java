/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.*;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int cpt = 0;
        Set<String> mots = new HashSet<>();
        while (sc.hasNextLine()) {
            String mot = sc.nextLine();
            /* Lisez les données et effectuez votre traitement */
            char last = mot.charAt(mot.length() - 1);
            if (mot.length() >= 5 && mot.length() <= 7) {
                if (last == 'a' || last == 'e' || last == 'i' || last == 'o' || last == 'u' || last == 'y') {
                    if (mot.charAt(0) + 1 == mot.charAt(1)) {
                        mots.add(mot);
                    }
                }
            }
        }
        System.out.println(mots.size());
    }
}