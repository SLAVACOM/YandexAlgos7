package com.slavacom.l1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class TaskJ {
    public static class Event {
        String name;
        int size;

        public Event(String[] input) {
            this.name = input[0];
            this.size = Integer.parseInt(input[1]);
        }

        public Event(String name, int size) {
            this.name = name;
            this.size = size;
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().trim().split(" ");

        int n = Integer.parseInt(input[0]);
        int d = Integer.parseInt(input[1]);

        ArrayList<Event> events = new ArrayList<>();
        ArrayList<Event> refuseEvents = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            input = reader.readLine().trim().split(" ");
            events.add(new Event(input));
        }

        events.sort(Comparator.comparingDouble(value -> value.size));


        while (true) {
            if (events.getFirst().size <= d) {
                refuseEvents.add(events.getFirst());
                events.removeFirst();
            } else {
                if (refuseEvents.size() > 0) {
                    int p = 0;
                    while (p < refuseEvents.size() && refuseEvents.get(p).size + d <= events.getFirst().size) {
                        p++;
                    }
                    if (p == refuseEvents.size()) {
                        refuseEvents.add(events.getFirst());
                        events.removeFirst();
                    } else {
                        refuseEvents.add(events.get(p));
                        events.remove(p);
                    }
                } else break;
            }
        }

        writer.write(String.valueOf(events.size()));
        writer.flush();
        writer.close();
    }


}
