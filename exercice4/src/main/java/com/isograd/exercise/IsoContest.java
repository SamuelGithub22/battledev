/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        List<String> sacha = Arrays.asList(sc.nextLine().split(" "));
        List<String> moi = Arrays.asList(sc.nextLine().split(" "));

        List<String> res = rec(sacha, moi, Collections.emptyList());
        if (res == null) {
            System.out.println(-1);
        } else {
            System.out.println(res.stream().collect(Collectors.joining(" ")));
        }
    }

    private static List<String> rec(List<String> sacha, List<String> moi, List<String> triche) {
        if (moi.isEmpty() && calcul(new LinkedList<>(sacha), new LinkedList<>(triche))) {
            return triche;
        }
        for (String s : moi) {
            List<String> moi2 = new LinkedList<>(moi);
            moi2.remove(s);
            List<String> triche2 = new LinkedList<>(triche);
            triche2.add(s);

            List<String> res = rec(sacha, moi2, triche2);
            if (res != null) {
                return res;
            }
        }
        return null;
    }


    public static boolean calcul(List<String> sacha, List<String> moi) {
        while (!sacha.isEmpty() && !moi.isEmpty()) {
            System.err.println(sacha + " "+moi);
            String s = sacha.get(0);
            String m = moi.get(0);
            int vic = vic(s, m);
            if (vic > 0) {
                moi.remove(0);
            } else if (vic < 0) {
                sacha.remove(0);
            } else {
                vic = vic(m, s);
                if (vic > 0) {
                    sacha.remove(0);
                } else if (vic < 0) {
                    moi.remove(0);
                } else {
                    sacha.remove(0);
                    moi.remove(0);
                }
            }
        }
        return !moi.isEmpty();
    }

    private static int vic(String s, String m) {
        if ("feu".equals(s)) {
            if ("eau".equals(m)) {
                return -1;
            } else if ("plante".equals(m)) {
                return 1;
            } else if ("glace".equals(m)) {
                return 1;
            }
        }
        if ("eau".equals(s)) {
            if ("plante".equals(m)) {
                return -1;
            } else if ("sol".equals(m)) {
                return -1;
            }
        }
        if ("plante".equals(s)) {
            if ("poison".equals(m)) {
                return 1;
            } else if ("sol".equals(m)) {
                return -1;
            } else if ("vol".equals(m)) {
                return 1;
            }
        }
        return 0;
    }

}