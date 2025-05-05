package com.slavacom.l3;

import java.util.*;
import java.io.*;

public class TaskJ {

    static class Triple {
        int offset;
        int length;
        char nextChar;

        public Triple(int offset, int length, char nextChar) {
            this.offset = offset;
            this.length = length;
            this.nextChar = nextChar;
        }

    }

    static class LZ77Coding {
        static final int WINDOW_SIZE = 4096; // размер окна поиска

        // Сжатие
        public static void pack(String s) {
            List<Triple> triples = new ArrayList<>();
            int i = 0;
            while (i < s.length()) {
                int bestOffset = 0;
                int bestLength = 0;

                int searchStart = Math.max(0, i - WINDOW_SIZE);
                for (int j = searchStart; j < i; j++) {
                    int length = 0;
                    while (i + length < s.length() && s.charAt(j + length) == s.charAt(i + length)) {
                        length++;
                        if (j + length >= i) break; // не выходить за границу
                    }
                    if (length > bestLength) {
                        bestLength = length;
                        bestOffset = i - j;
                    }
                }

                char nextChar = (i + bestLength < s.length()) ? s.charAt(i + bestLength) : 0;
                triples.add(new Triple(bestOffset, bestLength, nextChar));
                i += bestLength + 1;
            }

            // Вывод: каждую тройку кодируем в 3 числа: offset, length, nextChar
            List<Integer> output = new ArrayList<>();
            for (Triple t : triples) {
                output.add(t.offset);
                output.add(t.length);
                output.add((int) t.nextChar);
            }

            System.out.println(output.size());
            for (int val : output) {
                System.out.print(val + " ");
            }
            System.out.flush();
            System.out.println();
        }

        // Распаковка
        public static void unpack(int n, List<Integer> list) {
            List<Triple> triples = new ArrayList<>();
            for (int i = 0; i < n; i += 3) {
                int offset = list.get(i);
                int length = list.get(i + 1);
                char nextChar = (char) (int) list.get(i + 2);
                triples.add(new Triple(offset, length, nextChar));
            }

            StringBuilder result = new StringBuilder();
            for (Triple t : triples) {
                int start = result.length() - t.offset;
                for (int i = 0; i < t.length; i++) {
                    result.append(result.charAt(start + i));
                }
                if (t.nextChar != 0) {
                    result.append(t.nextChar);
                }
            }

            System.out.println(result.toString());
            System.out.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine().trim();

        if (command.equals("pack")) {
            String s = scanner.nextLine().trim();
            LZ77Coding.pack(s);
        } else if (command.equals("unpack")) {
            int n = Integer.parseInt(scanner.nextLine().trim());
            List<Integer> list = new ArrayList<>();
            String[] parts = scanner.nextLine().split(" ");
            for (String p : parts) {
                list.add(Integer.parseInt(p));
            }
            LZ77Coding.unpack(n, list);
        }
        scanner.close();
    }
}
