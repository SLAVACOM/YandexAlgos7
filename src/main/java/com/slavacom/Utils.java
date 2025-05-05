package com.slavacom;

import java.util.Random;

public class Utils {
    public static void main(String[] args) {
        Random rand = new Random();
        int n = 20;
        for (int i = 0; i < n; i++) {
            System.out.printf("%d ", rand.nextInt(100));
        }

    }
}
