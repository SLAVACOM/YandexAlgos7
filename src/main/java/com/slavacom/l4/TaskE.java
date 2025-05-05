package com.slavacom.l4;

import java.io.*;
import java.util.*;

public class TaskE {

    static class Player {
        int rating;
        int prev, next;
        int roundEliminated = 0;

        Player(int rating, int prev, int next) {
            this.rating = rating;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        int[] ratings = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        Player[] players = new Player[n];
        for (int i = 0; i < n; i++){
            int prev = (i - 1 + n) % n;
            int next = (i + 1) % n;
            players[i] = new Player(ratings[i], prev, next);
        }

        for (int i = 0; i < n; i++) {
            players[i].prev = (i - 1 + n) % n;
            players[i].next = (i + 1) % n;
        }

        boolean[] alive = new boolean[n];
        Arrays.fill(alive, true);
        Queue<Integer> queue = new ArrayDeque<>();


        for (int i = 0; i < n; i++) {
            if (isWeak(players, alive, i)) queue.add(i);
        }

        int round = 1;
        int aliveCount = n;

        while (!queue.isEmpty() && aliveCount > 2) {
            int size = queue.size();
            List<Integer> eliminated = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                int idx = queue.poll();

                if (!alive[idx] || !isWeak(players, alive, idx)) continue;

                players[idx].roundEliminated = round;
                eliminated.add(idx);
            }

            if (eliminated.isEmpty()) break;
            if (aliveCount  - eliminated.size() < 3) break;

            for (int idx : eliminated) {
                alive[idx] = false;
                aliveCount--;

                int prev = players[idx].prev;
                int next = players[idx].next;

                players[prev].next = next;
                players[next].prev = prev;

                if (alive[prev] && isWeak(players, alive, prev)) queue.add(prev);
                if (alive[next] && isWeak(players, alive, next)) queue.add(next);
            }
            round++;
        }

        for (Player player : players) writer.write(player.roundEliminated + " ");

        writer.flush();
        writer.close();
    }

    static boolean isWeak(Player[] players, boolean[] alive, int i) {
        int prev = players[i].prev;
        int next = players[i].next;

        while (!alive[prev] && prev != i) prev = players[prev].prev;
        while (!alive[next] && next != i) next = players[next].next;
        return players[i].rating < players[prev].rating && players[i].rating < players[next].rating;
    }
}
