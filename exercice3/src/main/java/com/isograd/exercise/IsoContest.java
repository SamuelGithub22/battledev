/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class IsoContest {
    public static void main( String[] argv ) throws Exception {
        Scanner sc = new Scanner(System.in);
        List<Point> listBl = new ArrayList<>();

        int N = sc.nextInt();

        while (sc.hasNextInt()) {
            listBl.add(new Point(sc.nextInt(), sc.nextInt()));
        }

        listBl.sort(Comparator.comparing(Point::getX));

        Iterator<Point> i = listBl.iterator();
        int NB = 1;
        Point curr = i.next();

        while (i.hasNext()) {
            Point bl = i.next();
            if (isContinue(curr, bl)) {
                curr = fusion(curr, bl);
            } else {
                NB++;
                curr = bl;
            }
        }

        System.out.println(NB);

    }

    private static Point fusion(Point curr, Point bl) {
        return new Point(curr.x, Math.max(curr.y, bl.y));
    }

    private static boolean isContinue(Point bl1, Point bl2) {
        return bl1.y + 1 >= bl2.x;
    }
}