package com.slavacom.l1;

import java.io.*;
import java.util.Arrays;

public class TaskD {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().trim().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] golds = new int[n];
        input = reader.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) golds[i] = Integer.parseInt(input[i]);
        reader.close();


        boolean[] results = new boolean[m + 1];
        results[0] = true;

        for (int gold : golds) {
            for (int i = m; i >= gold; i--) {
                if (results[i - gold]) results[i] = true;
            }
        }

        int result = m;
        while (!results[result]) result--;

        writer.write(String.format("%d", result));
        writer.flush();
        writer.close();


    }
}
