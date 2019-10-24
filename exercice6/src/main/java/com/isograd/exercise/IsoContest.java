/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String m1 = sc.nextLine();
        String m2 = sc.nextLine();

        System.err.println(m1);
        System.err.println(m2);

//        System.out.println(process(m1, m2));

        StringBuilder flag = new StringBuilder();
        for (int i = 0; i < m1.length(); i++) {
            flag.append('?');
        }
        for (int i = 1; i < m1.length(); i++) {
            System.err.println(i);
            if (!process2(m1,m2, flag, i)) {
                System.out.println(i - 1);
                return;
            }
        }
        System.out.println(0);
    }


    private static boolean process2(String m1, String m2, StringBuilder flag, int nb) {
        boolean ok = false;
//        for (int i = flag.indexOf("X"); i != -1 && i+nb < m1.length(); i =flag.indexOf("X", i)) {
        for (int i = 0; i+nb  <= m1.length(); i ++) {
//            int no = flag.indexOf("0", i);
//            if (no !=-1 && no <i + nb) {
//                System.err.println(no);
//                System.err.println(m1.substring(i, i+nb));
////                i+= no;
////        System.err.println(no);
////        System.err.println(m1);
////        System.err.println(flag);
////        System.err.println(m2);
//                continue;
//            }

//            if (flag.substring(i, i+nb).contains("0")) {
//                i+= flag.indexOf("0", i)-1;
//                continue;
//            }
            String m = m1.substring(i, i+nb);
//            if (m.contains("0")) {
//                continue;
//            }


            if (m2.contains(m)) {
                for (int j = i; j < i+nb; j++) {
                    flag.setCharAt(j, 'X');
                }
                ok = true;
            } else {
                for (int j = i; j < i+nb; j++) {
                    flag.setCharAt(j, '0');
                }
            }
        }
//        System.err.println(nb);
//        System.err.println(m1);
//        System.err.println(flag);
//        System.err.println(m2);
        return ok;
    }

    private static int process(String m1, String m2) {
        if (m1.equals(m2)) {
            return m1.length();
        } else if (m1.isEmpty() || m2.isEmpty()) {
            return 0;
        } else {
//            if ("bon".equals(m2))
//                System.err.println(m1 + "|" + m2);
            String _m1 = m1.substring(1);
            String _m2 = m2.substring(1);
            String m1_=m1.substring(0, m1.length() - 1);
            String m2_=m2.substring(0, m2.length() - 1);
            int i = process(m1.substring(1), m2);
            i = Math.max(i, process(_m1, _m2));
            i = Math.max(i, process(_m1, m2_));
            i = Math.max(i, process(m1, m2_));

            i = Math.max(i, process(m1, _m2));
            i = Math.max(i, process(_m1, _m2));
            i = Math.max(i, process(m1_, _m2));
            i = Math.max(i, process(m1_, m2));
            return i;
        }

//        if (m2.length()>1) {
////            int i = process(m1.substring(1), m2);
////            i = Math.max(i, process(m1.substring(1), m2.substring(1)));
////            i = Math.max(i, process(m1.substring(1), m2.substring(0, m2.length() - 2)));
////            return i;
////        } else if (m1.length()>1) {
////            int i = process(m1, m2.substring(1));
////            i = Math.max(i,process(m1.substring(1), m2.substring(1)));
////            i = Math.max(i, process(m1.substring(0, m1.length() - 2), m2.substring(1)));
////            return i;
////        } else {
////            return 0;
////        }

//            {
//                if ("bon".equals(m2))
//                    System.err.println(m1 + "|" + m2);
//                int i = process(m1.substring(1), m2);
//                i = Math.max(i, process(m1.substring(1), m2.substring(1)));
//                i = Math.max(i, process(m1.substring(1), m2.substring(0, m2.length() - 2)));
//
//                i = Math.max(i, process(m1, m2.substring(1)));
//                //i = Math.max(i,process(m1.substring(1), m2.substring(1)));
//                i = Math.max(i, process(m1.substring(0, m1.length() - 2), m2.substring(1)));
//                return i;
//        }
    }
}