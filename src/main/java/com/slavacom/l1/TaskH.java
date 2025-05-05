package com.slavacom.l1;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class TaskH {
    static class Order {
        public int length;
        private int evenSCount, oddSCount;
        private final String str;

        private Order(String str) {
            this.str = str;
            this.length = str.length();
            this.evenSCount = 0;
            this.oddSCount = 0;
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) == 'S') {
                    if (i % 2 == 0) evenSCount++;
                    else oddSCount++;
                }
            }
        }

        public int difference() {
            return evenSCount - oddSCount;
        }

        public int getEvenSCount() {
            return evenSCount;
        }

        public int getOddSCount() {
            return oddSCount;
        }

        @Override
        public String toString() {
            return "Order{" + "evenSCount=" + evenSCount + ", length=" + length + ", oddSCount=" + oddSCount + ", str='" + str + '\'' + '}';
        }


    }

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine().trim());
        Order[] orders = new Order[n];

        for (int i = 0; i < n; i++) {
            String str = reader.readLine().trim();
            orders[i] = new Order(str);
        }
        reader.close();

        Arrays.sort(orders, Comparator.comparing(Order::difference).reversed());

        Arrays.stream(orders).forEach(order -> {
            System.out.print(order.str + " ");
        });
        int vasyaSimpleCount = 0;
        int offset = 0;

        for (Order order : orders) {
            if (offset % 2 == 0) vasyaSimpleCount += order.getEvenSCount();
            else vasyaSimpleCount += order.getOddSCount();
            offset += order.length;
        }
        writer.write(String.valueOf(vasyaSimpleCount));
        writer.flush();
        writer.close();
    }
}
