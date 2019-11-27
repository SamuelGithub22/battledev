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
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        sc.nextLine();
        ArrayList<Valeur> valeurs = new ArrayList<>();
        Pierre[] pierre = new Pierre[n];
        for (int i = 0; i < n; i++) {
//            pierre[i] = new Pierre(sc.nextInt(), sc.nextInt());
            valeurs.add(new Valeur(sc.nextInt(), 1, sc.nextInt()));
        }
        Poudre[] poudre = new Poudre[c];
        for (int i = 0; i < m; i++) {
//            poudre[i] = new Poudre(sc.nextInt(), sc.nextInt());
            valeurs.add(new Valeur(sc.nextInt(), sc.nextInt(), 1));
        }

        valeurs.sort(Comparator.comparing(Valeur::prixParGramme).reversed());

//        List<Precieux> precieux = new ArrayList<>();
//        Arrays.stream(pierre).filter(Objects::nonNull).forEach(precieux::add);
//        Arrays.stream(poudre).filter(Objects::nonNull).forEach(precieux::add);
//
//        precieux.sort(Comparator.comparing(Precieux::max));
//
//        System.err.println(precieux);

        System.err.println(valeurs);

        int reste = c;
        int valeur = 0;
        for (Valeur p : valeurs) {
            int qte = 1;
            while (qte <= p.quantite && reste >= p.gramme) {
                System.err.println(p);
                if (p.gramme <= reste) {
                    valeur += p.valeur;
                    reste -= p.gramme;
                }
                qte++;
            }
        }
        System.out.println(valeur);

        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }



    public static class Valeur {
        int quantite;
        int valeur;
        int gramme;

        public Valeur(int valeur, int quantite, int gramme) {
            this.valeur = valeur;
            this.quantite = quantite;
            this.gramme = gramme;
        }

        @Override
        public String toString() {
            return "Valeur " + gramme + "g " + quantite + "q " + valeur + "po";
        }

        public double prixParGramme() {
            return valeur / gramme;
        }
    }



    public interface Precieux {
        int max();
        int valeur(int gramme);
        int gramme(int gramme);
    }

    public static class Pierre implements Precieux{
        int poids;
        int valeur;

        public Pierre(int valeur, int poids) {
            this.valeur = valeur;
            this.poids = poids;
        }

        @Override
        public int max() {
            return poids;
        }

        @Override
        public int valeur(int gramme) {
            return valeur;
        }

        @Override
        public int gramme(int gramme) {
            return gramme > poids ? poids : 0;
        }

        @Override
        public String toString() {
            return "Pierre " + poids + "g " + valeur + "po";
        }
    }

    public static class Poudre implements Precieux{
        int quantite;
        int prix;

        public Poudre(int prix, int quantite) {
            this.prix = prix;
            this.quantite = quantite;
        }

        @Override
        public int max() {
            return prix * quantite;
        }

        @Override
        public int valeur(int gramme) {
            gramme = Math.min(gramme, quantite);
            return gramme * prix;
        }

        @Override
        public int gramme(int gramme) {
            return gramme;
        }

        @Override
        public String toString() {
            return "Poudre " + quantite + "g " + prix + "po/g max:" + max() + "po ";
        }
    }
}