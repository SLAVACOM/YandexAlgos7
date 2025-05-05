package com.slavacom.l2;

import java.io.*;

public class TaskС {

    public static class SegmentTree {
        int n;
        int[] max;
        int[] index;

        SegmentTree(int[] data) {
            this.n = data.length;
            max = new int[2 * n];
            index = new int[2 * n];

            for (int i = 0; i < n; i++) {
                max[n + i] = data[i];
                index[n + i] = i;
            }

            for (int i = n - 1; i > 0; i--) {
                int left = i << 1;
                int right = i << 1 | 1;
                if (max[left] > max[right]) {
                    max[i] = max[left];
                    index[i] = index[left];
                } else {
                    max[i] = max[right];
                    index[i] = index[right];
                }
            }
        }

        int[] query(int left, int right) {
            left += n;
            right += n;
            int resMax = Integer.MIN_VALUE;
            int resIndex = -1;

            while (left < right) {
                if ((left & 1) == 1) {
                    if (max[left] > resMax) {
                        resMax = max[left];
                        resIndex = index[left];
                    }
                    left++;
                }
                if ((right & 1) == 1) {
                    right--;
                    if (max[right] > resMax) {
                        resMax = max[right];
                        resIndex = index[right];
                    }
                }
                left >>= 1;
                right >>= 1;
            }
            resIndex += 1;
            return new int[]{resMax, resIndex};
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
            int left = Integer.parseInt(input[0]) - 1;
            int right = Integer.parseInt(input[1]);

            int[] result = segmentTree.query(left, right);
            writer.write(String.format("%d %d\n", result[0], result[1]));
        }
        reader.close();
        writer.flush();
        writer.close();
    }
}
