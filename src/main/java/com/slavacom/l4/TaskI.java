package com.slavacom.l4;

import java.io.*;

public class TaskI {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());

        int[] mass = new int[n + 1];
        int[] prev = new int[n + 1];

        long totalMass = 0;

        for (int i = 1; i <= n; i++) {
            String[] parts = reader.readLine().split(" ");
            int t = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);

            if (m > 0) {
                mass[i] = mass[t] + m;
                prev[i] = t;
            } else {
                mass[i] = mass[prev[t]];
                prev[i] = prev[prev[t]];
            }
            totalMass += mass[i];
        }

        writer.write(String.valueOf(totalMass));
        writer.flush();
        reader.close();
        writer.close();

    }
}
