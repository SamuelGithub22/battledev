/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        String  line;
        Scanner sc = new Scanner(System.in);
        int nb = Integer.parseInt(sc.nextLine());
        int d1 = time("7:59");
        int d2 = time("20:00");
        int i = 0;
        while(sc.hasNextLine()) {
            int t = time(sc.nextLine());
            if (t <= d1 || t >= d2) {
                i++;
            }
            if (i > (nb / 2)) {
                System.out.println("SUSPICIOUS");
                return;
            }
            /* Lisez les données et effectuez votre traitement */
        }

        System.out.println("OK");
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    static Pattern p = Pattern.compile("(\\d\\d?):(\\d\\d?)");

    private static int time(String s) {
        Matcher m = p.matcher(s);
        if (m.find()) {
            return Integer.parseInt(m.group(1)) * 60 + Integer.parseInt(m.group(2));
        }
        return 0;
    }
}