package com.slavacom.l1;

import java.io.*;
import java.util.Arrays;

public class TaskE {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().trim().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] mass = new int[n];
        int[] price = new int[n];

        input = reader.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) mass[i] = Integer.parseInt(input[i]);

        input = reader.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) price[i] = Integer.parseInt(input[i]);
        reader.close();

        int[] results = new int[m + 1];
        Arrays.fill(results, 1, m + 1, -1);
        results[0] = 0;


        for (int i = 0; i < n; i++) {
            for (int j = m; j >= mass[i]; j--) {
                if (results[j - mass[i]] != -1) {
                    results[j] = Math.max(results[j], results[j - mass[i]] + price[i]);
                }
            }
        }

        int result = 0;
        for (int i = 0; i <= m; i++) result = Math.max(result, results[i]);


        writer.write(String.format("%d", result));
        writer.flush();
        writer.close();


    }
}
