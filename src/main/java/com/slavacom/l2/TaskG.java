package com.slavacom.l2;

import java.io.*;


public class TaskG {

    public static class Node {
        int prefixZero, suffixZero, maxZero, length;

        Node(int prefixZero, int suffixZero, int maxZero, int length) {
            this.prefixZero = prefixZero;
            this.suffixZero = suffixZero;
            this.maxZero = maxZero;
            this.length = length;
        }

        static Node fromValue(int value) {
            if (value == 0) return new Node(1, 1, 1, 1);
            else return new Node(0, 0, 0, 1);
        }

        static Node merge(Node l, Node r) {
            int prefix = l.prefixZero == l.length ? l.length + r.prefixZero : l.prefixZero;
            int suffix = r.suffixZero == r.length ? r.length + l.suffixZero : r.suffixZero;
            int max = Math.max(Math.max(l.maxZero, r.maxZero), l.prefixZero + r.prefixZero);
            return new Node(prefix, suffix, max, l.length + r.length);
        }


    }

    public static class SegmentTree {
        int n;
        Node[] tree;

        public SegmentTree(int[] arr) {
            n = arr.length;
            tree = new Node[2 * n];
            build(arr, 1, 0, n - 1);

        }

        private void build(int[] arr, int v, int tl, int tr) {
            if (tr == tl) {
                tree[v] = Node.fromValue(arr[tl]);
            } else {
                int tm = (tl + tr) / 2;
                build(arr, v * 2, tl, tm);
                build(arr, v * 2 + 1, tm + 1, tr);
                tree[v] = Node.merge(tree[v * 2], tree[v * 2 + 1]);
            }
        }

        public void update(int v, int tl, int tr, int pos, int newVal) {
            if (tl == tr) tree[v] = Node.fromValue(newVal);
            else {
                int tm = (tl + tr) / 2;
                if (pos <= tm) update(v * 2, tl, tm, pos, newVal);
                else update(v * 2 + 1, tm + 1, tr, pos, newVal);
                tree[v] = Node.merge(tree[v * 2], tree[v * 2 + 1]);
            }
        }

        public Node query(int v, int tl, int tr, int l, int r) {
            if (l > r) return new Node(0, 0, 0, 0);
            if (l == tl && r == tr) return tree[v];
            int tm = (tl + tr) / 2;
            Node left = query(v * 2, tl, tm, l, Math.min(r, tm));
            Node right = query(v * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);

            return Node.merge(left, right);
        }

        public void update(int pos, int newVal) {
            update(1, 0, n - 1, pos, newVal);
        }

        public int getMaxZero(int l, int r) {
            return query(1, 0, n - 1, l, r).maxZero;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine().trim());

        String[] input = reader.readLine().trim().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(input[i]);

        SegmentTree tree = new SegmentTree(arr);

        int k = Integer.parseInt(reader.readLine().trim());
        for (int i = 0; i < k; i++) {
            String[] query = reader.readLine().trim().split(" ");
            int l = Integer.parseInt(query[1]);
            int r = Integer.parseInt(query[2]);

            if (query[0].equals("QUERY")) writer.write(tree.getMaxZero(l, r) + "\n");
            else tree.update(l, r);
        }

        reader.close();
        writer.flush();
        writer.close();
    }
}
