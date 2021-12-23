/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;

import static java.lang.Integer.parseInt;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        System.err.println("----------------------");
        String  line;
        Scanner sc = new Scanner(System.in);
        List<BandeLumineuse> listBl = new ArrayList<>();

        int nbLignes = Integer.parseInt(sc.nextLine());
        System.err.println(nbLignes);

        for (int i=0; i<nbLignes;i++) {
            line = sc.nextLine();
            if (nbLignes == 2)
            System.err.println(line);
            String[] intervalle = line.split(" ");
            int debut = parseInt(intervalle[0]);
            int fin = parseInt(intervalle[1]);
            BandeLumineuse bd = new BandeLumineuse(debut, fin);
            listBl.add(bd);
        }

        listBl.sort(Comparator.comparing(BandeLumineuse::getDebut));

        Iterator<BandeLumineuse> i = listBl.iterator();
        int resultat = 1;
        BandeLumineuse curr = i.next();

        while (i.hasNext()) {
            BandeLumineuse bl = i.next();
            if (isContinue(curr, bl)) {
                curr = fusion(curr, bl);
            } else {
                resultat++;
                curr = bl;
            }
        }

        System.out.println(resultat);

    }

    private static BandeLumineuse fusion(BandeLumineuse curr, BandeLumineuse bl) {
        return new BandeLumineuse(curr.getDebut(), Math.max(curr.getFin(), bl.getFin()));
    }

    private static boolean isContinue(BandeLumineuse bl1, BandeLumineuse bl2) {
        return bl1.getFin()+1 >= bl2.getDebut();
    }
}

class BandeLumineuse {
    private int debut;
    private int fin;

    public BandeLumineuse(int debut, int fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public int getDebut() {
        return debut;
    }

    public int getFin() {
        return fin;
    }

    @Override
    public String toString() {
        return "BL{" +
                "" + debut +
                "," + fin +
                '}';
    }

}