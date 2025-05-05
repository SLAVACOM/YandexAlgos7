package com.slavacom.l2;

import java.io.*;

public class TaskA {

    public static class SegmentTree {
        int n;
        int[] max;
        int[] count;

        SegmentTree(int[] data) {
            this.n = data.length;

            max = new int[2 * n];
            count = new int[2 * n];

            for (int i = 0; i < n; i++) {
                max[n + i] = data[i];
                count[n + i] = 1;
            }

            for (int i = n - 1; i > 0; i--) {
                if (max[i << 1] > max[i << 1 | 1]) {
                    max[i] = max[i << 1];
                    count[i] = count[i << 1];
                } else if (max[i << 1] < max[i << 1 | 1]) {
                    max[i] = max[i << 1 | 1];
                    count[i] = count[i << 1 | 1];
                } else {
                    max[i] = max[i << 1];
                    count[i] = count[i << 1] + count[i << 1 | 1];
                }
            }
        }

        int[] query(int left, int right) {
            left += n;
            right += n;
            int resMax = Integer.MIN_VALUE;
            int resCount = 0;

            while (left < right) {
                if ((left & 1) == 1) {
                    if (max[left] > resMax) {
                        resMax = max[left];
                        resCount = count[left];
                    } else if (max[left] == resMax) {
                        resCount += count[left];
                    }
                    left++;
                }
                if ((right & 1) == 1) {
                    right--;
                    if (max[right] > resMax) {
                        resMax = max[right];
                        resCount = count[right];
                    } else if (max[right] == resMax) {
                        resCount += count[right];
                    }
                }
                left >>= 1;
                right >>= 1;
            }
            return new int[]{resMax, resCount};
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
