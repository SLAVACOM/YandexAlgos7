package com.slavacom.l1;

import java.io.*;

public class TaskC {
    static long[] prices = new long[31];

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int m = Integer.parseInt(reader.readLine().trim());


        String[] input = reader.readLine().trim().split(" ");
        reader.close();

        prices[0] = Long.parseLong(input[0]);

        for (int i = 1; i < 31; i++) prices[i] = Math.max(Long.parseLong(input[i]), 2 * prices[i - 1]);

        long result = rec_search(m);

        writer.write(String.valueOf(result));

        writer.flush();
        writer.close();
    }

    public static long rec_search(int item) {
        if (item <= 0) return 0;

        int i = 30;
        while (i > 0 && prices[i] > item) i--;

        return Math.min((1L << i) + rec_search((int) (item - prices[i])), 1L << (i + 1));
    }
}


