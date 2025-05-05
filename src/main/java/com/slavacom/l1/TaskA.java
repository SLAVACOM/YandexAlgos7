package com.slavacom.l1;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class TaskA {
    public static class Group {
        int persons, index;

        public Group(int index, int persons) {
            this.index = index;
            this.persons = persons;
        }

        @Override
        public String toString() {
            return "Group{" +
                    "index=" + index +
                    ", persons=" + persons +
                    '}';
        }
    }

    public static class Room {
        int computers, index;

        public Room(int index, int computers) {
            this.index = index;
            this.computers = computers;
        }

        @Override
        public String toString() {
            return "Room{" +
                    "computers=" + computers +
                    ", index=" + index +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] input = reader.readLine().trim().split(" ");

        int n = (Integer.parseInt(input[0]));
        int m = (Integer.parseInt(input[1]));

        Group[] groups = new Group[n];
        Room[] rooms = new Room[m];

        input = reader.readLine().trim().split(" ");
        for (int i = 0; i < n; i++) groups[i] = new Group(i, Integer.parseInt(input[i]) + 1);

        input = reader.readLine().trim().split(" ");
        for (int i = 0; i < m; i++) rooms[i] = new Room(i, Integer.parseInt(input[i]));

        reader.close();

        Arrays.sort(groups, Comparator.comparingInt(g -> g.persons));
        Arrays.sort(rooms, Comparator.comparing(r -> r.computers));


        int[] result = new int[n];
        Arrays.fill(result, -1);
        int roomPtr = 0;
        for (Group group : groups) {
            while (roomPtr < m && rooms[roomPtr].computers < group.persons) roomPtr++;
            if (roomPtr == m) break;
            result[group.index] = rooms[roomPtr].index + 1;
            roomPtr++;
        }

        int usedRooms = 0;

        for (int i = 0; i < n; i++) if (result[i] != -1) usedRooms++;

        writer.write(String.format("%d\n", usedRooms));

        for (int i = 0; i < n; i++) {
            if (result[i] == -1) writer.write("0 ");
            else writer.write(String.format("%d ", result[i]));
        }
        writer.flush();
        writer.close();

    }
}