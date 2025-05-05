package com.slavacom.l1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TaskB {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(reader.readLine().trim());

        for (int i = 0; i < t; i++) {
            int[] numbers = getInputArray(reader);
            int[] segments = getSegments(numbers);
            writer.write(String.format("%d\n", segments.length));
            for (int segment : segments) writer.write(String.format("%d ", segment));
            writer.newLine();
        }
        reader.close();

        writer.flush();
        writer.close();
    }


    public static int[] getSegments(int[] arr) {
        List<Integer> segmentList = new ArrayList<>();

        int currentLength = 0;
        int minElement = Integer.MAX_VALUE;
        for (int num : arr) {
            currentLength++;
            if (num < minElement) minElement = num;

            if (currentLength >= minElement) {
                segmentList.add(currentLength);
                currentLength = 0;
                minElement = Integer.MAX_VALUE;
            }

        }
        if (currentLength > 0) segmentList.add(currentLength);

        return segmentList.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] getInputArray(BufferedReader reader) throws IOException {
        int n = Integer.parseInt(reader.readLine().trim());
        String[] input = reader.readLine().trim().split(" ");

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(input[i]);
        return arr;
    }
}
