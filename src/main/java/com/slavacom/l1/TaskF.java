package com.slavacom.l1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TaskF {

    public static class Gold {
        int price;
        int prev;

        public Gold(int price, int prev) {
            this.price = price;
            this.prev = prev;
        }

        public Gold() {
            this.prev = 0;
            this.price = 0;
        }

        public int getPrev() {
            return prev;
        }

        public void setPrev(int prev) {
            this.prev = prev;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().trim().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);

        int[] mass = new int[n];
        int[] price = new int[n];

        input = reader.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) mass[i] = Integer.parseInt(input[i]);

        input = reader.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) price[i] = Integer.parseInt(input[i]);
        reader.close();

        Gold[][] results = new Gold[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) results[i][j] = new Gold();
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                results[i][j] = results[i - 1][j];

                if (j >= mass[i - 1]) {
                    int newPrice = results[i - 1][j - mass[i - 1]].getPrice() + price[i - 1];
                    if (newPrice > results[i][j].getPrice())
                        results[i][j] = new Gold(newPrice, i - 1);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        int j = m;
        for (int i = n; i > 0; i--) {
            if (results[i][j].getPrev() != -1 && results[i][j].getPrice() != results[i - 1][j].getPrice()) {
                result.add(i);
                j -= mass[results[i][j].getPrev()];
            }
        }

        for (int i = 1; i <= result.size(); i++) {
            writer.write(String.format("%d\n", result.get(result.size() - i )));
        }

        writer.flush();
        writer.close();


    }
}
