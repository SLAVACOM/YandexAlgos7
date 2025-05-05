package com.slavacom.l2;

import java.io.*;

public class TaskH {

    public static class SegmentTree {
        int n;
        int[] tree;
        int[] lazy;

        SegmentTree(int[] data) {
            this.n = data.length;
            tree = new int[2 * n];
            lazy = new int[2 * n];

            System.arraycopy(data, 0, tree, n, n);

            for (int i = n - 1; i >= 0; i--) {
                tree[i] = Math.max(tree[i << 1], tree[i << 1 | 1]);
            }
        }

        public void applyLazy(int index) {
            if (lazy[index] != 0) {
                tree[index] += lazy[index];
                if (index < n) {
                    lazy[index << 1] += lazy[index];
                    lazy[index << 1 | 1] += lazy[index];
                }
                lazy[index] = 0;
            }
        }

        public void update(int left, int right, int value) {
            left += n;
            right += n;


            while (left < right) {
                applyLazy(left);
                applyLazy(right);
                if ((left & 1) == 1) {
                    lazy[left] += value;
                    applyLazy(left);
                    left++;
                }
                if ((right & 1) == 1) {
                    right--;
                    lazy[right] += value;
                    applyLazy(right);
                }
                left >>= 1;
                right >>= 1;
            }
        }


        int getN(int index) {
            index += n;
            applyLazy(index);
            return tree[index];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine().trim());

        int[] arr = new int[n];
        String[] input = reader.readLine().trim().split(" ");

        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(input[i]);
        int k = Integer.parseInt(reader.readLine().trim());

        SegmentTree segmentTree = new SegmentTree(arr);
        for (int i = 0; i < k; i++) {
            input = reader.readLine().trim().split(" ");
            if (input[0].equals("a")) {
                int index = Integer.parseInt(input[1]) - 1;
                int value = Integer.parseInt(input[2]);
                int addValue = Integer.parseInt(input[3]);
                segmentTree.update(index, value, addValue);
            } else if (input[0].equals("g")) {
                int index = Integer.parseInt(input[1]) - 1;
                writer.write(String.format("%d\n", segmentTree.getN(index)));
            }
        }

        reader.close();
        writer.flush();
        writer.close();
    }
}
