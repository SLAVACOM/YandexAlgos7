package com.slavacom.l4;

import java.io.*;

public class TaskG {

    static int[] parent;
    static int[] rank;

    public static void main(String[] args) throws IOException {


        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = reader.readLine().trim().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        parent = new int[n + 1];
        rank = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        int components = n;

        for (int i = 1; i <= m; i++) {
            input = reader.readLine().trim().split(" ");
            int x = Integer.parseInt(input[0]);
            int y = Integer.parseInt(input[1]);
            if (union(x, y)) components--;
            if (components == 1) {
                reader.close();
                writer.write(String.valueOf(i));
                writer.flush();
                writer.close();
                return;
            }
        }
    }


    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY) return false;

        if (rank[rootX] < rank[rootY]) parent[rootX] = rootY;
        else {
            parent[rootY] = rootX;
            if (rank[rootX] == rank[rootY]) rank[rootX]++;
        }
        return true;
    }


}
