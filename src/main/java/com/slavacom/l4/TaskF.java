package com.slavacom.l4;

import java.io.*;

public class TaskF {
    static int[] state;
    static int[] keys;
    static int cycles = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        keys = new int[n];
        state = new int[n];

        for (int i = 0; i < n; i++) {
            keys[i] = Integer.parseInt(reader.readLine()) - 1;
        }
        reader.close();

        for (int i = 0; i < n; i++){
            if (state[i] == 0) dfs(i);
        }


        writer.write(String.valueOf(cycles));
        writer.flush();
        writer.close();
    }

    private static void dfs(int v) {
        state[v] = 1;
        int next = keys[v];
        if (state[next] == 0) dfs(next);
        else if (state[next] == 1) cycles++;
        state[v] = 2;
    }
}
