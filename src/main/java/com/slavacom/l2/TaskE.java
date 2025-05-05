package com.slavacom.l2;

import java.io.*;

public class TaskE {

    public static class SegmentTree {
        int n;
        int[] tree;

        SegmentTree(int[] data) {
            this.n = data.length;
            tree = new int[2 * n];

            for (int i = 0; i < n; i++) {
                tree[n + i] = (data[i] == 0 ? 1 : 0);
            }

            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[i << 1] + tree[i << 1 | 1];
            }
        }

        void update(int index, int value) {
            index += n;
            tree[index] = (value == 0 ? 1 : 0);

            for (index >>= 1; index > 0; index >>= 1) {
                tree[index] = tree[index << 1] + tree[index << 1 | 1];
            }
        }

        private int descend(int index, int k) {
            while (index < n) {
                if (tree[index << 1] >= k) {
                    index <<= 1;
                } else {
                    k -= tree[index << 1];
                    index = (index << 1) | 1;
                }
            }
            return index;
        }

        int query(int left, int right, int k) {
            left += n;
            right += n;

            int pos = -1;

            while (left < right) {
                if ((left & 1) == 1) {
                    if (tree[left] >= k) {
                        pos = descend(left, k);
                        break;
                    } else {
                        k -= tree[left];
                        left++;
                    }
                }
                if ((right & 1) == 1) {
                    right--;

                    if (tree[right] >= k) {
                        pos = descend(right, k);
                        break;
                    } else {
                        k -= tree[right];
                    }
                }
                left >>= 1;
                right >>= 1;
            }
            return pos == -1 ? -1 : (pos - n + 1);
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

            if (input[0].equals("u")) {
                int index = Integer.parseInt(input[1])-1;
                int value = Integer.parseInt(input[2]);
                segmentTree.update(index, value);
            } else if (input[0].equals("s")) {
                int left = Integer.parseInt(input[1])-1;
                int right = Integer.parseInt(input[2]);
                int kValue = Integer.parseInt(input[3]);
                int result = segmentTree.query(left, right, kValue);
                writer.write(String.format("%d ", result));
            }
        }

        reader.close();
        writer.flush();
        writer.close();
    }
}
