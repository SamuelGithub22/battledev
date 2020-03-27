/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();

        int[][] cal = new int[6][24*60];
        Pattern p = Pattern.compile("(\\d+) (\\d+)\\:(\\d+)\\-(\\d+)\\:(\\d+)");

        while(sc.hasNextLine()) {
            String line = sc.nextLine();
            Matcher m = p.matcher(line);
            if (m.find()) {
                int jour = Integer.valueOf(m.group(1));
                int m1 = Integer.valueOf(m.group(2)) * 60 + Integer.valueOf(m.group(3));
                int m2 = Integer.valueOf(m.group(4)) * 60 + Integer.valueOf(m.group(5));
                for (int i = m1; i <= m2 ; i++) {
                    cal[jour][i] ++;
                }
            }
            /* Lisez les données et effectuez votre traitement */
        }
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
        for (int i = 1; i < cal.length; i++) {
            int[] jour = cal[i];
            int count = 0;
            int deb = 8 * 60;
            for (int j = 8 * 60; j < 18*60; j++) {
                int c = jour[j];
                if (c == 0) {
                    count++;
                } else {
                    count = 0;
                    deb = j + 1;
                }
                if (count >= 60) {
                    System.out.println(i + " " + (deb / 60) + ":" + (deb % 60) + "-" + (j / 60) + ":" + (j % 60));
                    return;
                }
            }
        }
        System.out.println("KO");
    }
}