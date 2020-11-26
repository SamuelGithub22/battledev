/*******
 * Read input from System.in
 * Use: System.out.println to ouput your result to STDOUT.
 * Use: System.err.println to ouput debugging information to STDERR.
 * ***/
package com.isograd.exercise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class IsoContest {
    public static void main(String[] argv) throws Exception {
        String line;
        Scanner sc = new Scanner(System.in);
        int nb = Integer.parseInt(sc.nextLine());
        HashMap<String, Node> map = new HashMap<>();
        while (sc.hasNextLine()) {
            line = sc.nextLine();
            String[] sp = line.split(" ");
            Node A = map.computeIfAbsent(sp[0], Node::new);
            Node B = map.computeIfAbsent(sp[1], Node::new);
            B.childs.add(A);
            A.parent = B;
//            System.err.println(A);
//            System.err.println(B);
//            System.err.println("----");
            /* Lisez les données et effectuez votre traitement */
        }

        Node root = map.values().stream().filter(n -> n.parent == null).findFirst().orElse(null);

        int [] count = new int[10];
        count(root, 0, count);
        System.out.println(Arrays.stream(count).mapToObj(Integer::toString).collect(Collectors.joining(" ")));

        System.err.println(root);
//        System.err.println();
        /* Vous pouvez aussi effectuer votre traitement une fois que vous avez lu toutes les données.*/
    }

    private static void count(Node n, int rang, int [] count) {
        count[rang]++;
        n.childs.forEach(c -> count(c, rang + 1, count));
    }

    private static class Node {
        private String name;
        private Node parent;
        private List<Node> childs = new LinkedList<>();

        public Node(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", childs=" + childs +
                    '}';
        }
    }
}