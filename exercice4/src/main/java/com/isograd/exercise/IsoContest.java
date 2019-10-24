/**
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 */
package com.isograd.exercise;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class IsoContest {

    /**
     * Executes the solution.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();
            scanner.nextLine();

            Set<String> set = cut(scanner.nextLine());
            for (int i = 0; i < n - 1 && !set.isEmpty(); i++) {
                set.retainAll(cut(scanner.nextLine()));
            }

            System.out.println(set.stream().max(Comparator.comparing(String::length)).orElse("KO"));
        }
    }

    /**
     *
     */
    private static Set<String> cut(String word) {
        Set<String> set = new HashSet<>();
        cut(set, word, "", 0);
        return set;
    }

    private static void cut(Set<String> set, String init, String current, int pos) {
        if (pos == init.length()) {
            if (!current.isEmpty()) {
                set.add(current);
            }
            return;
        }
        // Taking the current letter.
        cut(set, init, current + init.charAt(pos), pos + 1);
        // Or not.
        cut(set, init, current, pos + 1);
    }

}

