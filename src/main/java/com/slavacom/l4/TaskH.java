package com.slavacom.l4;

import java.io.*;
import java.util.*;

public class TaskH {

    static class DSU {
        int[] parent, rank;

        public DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        void union(int x, int y) {
            int xr = find(x), yr = find(y);
            if (xr == yr) return;
            if (rank[xr] < rank[yr]) parent[xr] = yr;
            else {
                parent[yr] = xr;
                if (rank[xr] == rank[yr]) rank[xr]++;
            }
        }
    }
    static class Operation {
        String type;
        int u, v;
        public Operation(String type, int u, int v) {
            this.type = type;
            this.u = u;
            this.v = v;
        }
    }



    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().trim().split(" ");

        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        int k = Integer.parseInt(input[2]);

        List<int[]> edges = new ArrayList<>();
        Map<String, Integer> edgeMap = new HashMap<>();


        for (int i = 0; i < m; i++) {
            input = reader.readLine().trim().split(" ");
            int u = Integer.parseInt(input[0]);
            int v = Integer.parseInt(input[1]);
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }
            edges.add(new int[]{u, v});
            edgeMap.put(u + "_" + v, i);
        }

        Operation[] operations = new Operation[k];
        boolean[] toDelete = new boolean[m];

        for (int i = 0; i < k; i++) {
            String[] op = reader.readLine().split(" ");
            String type = op[0];
            int u = Integer.parseInt(op[1]);
            int v = Integer.parseInt(op[2]);
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }

            operations[i] = new Operation(type, u, v);
            if (type.equals("cut")) {
                int idx = edgeMap.get(u + "_" + v);
                toDelete[idx] = true;
            }
        }

        DSU dsu = new DSU(n);
        for (int i = 0; i < m; i++) {
            if (!toDelete[i]) {
                int[] e = edges.get(i);
                dsu.union(e[0], e[1]);
            }
        }

        List<String> results = new ArrayList<>();

        for (int i = k - 1; i >= 0; i--) {
            Operation op = operations[i];
            if (op.type.equals("ask")) {
                if (dsu.find(op.u) == dsu.find(op.v)) results.add("YES");
                else results.add("NO");
            } else dsu.union(op.u, op.v);
        }

        for (int i = results.size() - 1; i >= 0; i--) {
            writer.write(results.get(i));
            writer.newLine();
        }

        writer.flush();
        reader.close();
        writer.close();
    }
}
