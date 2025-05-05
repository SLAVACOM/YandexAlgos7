package com.slavacom.l4;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;


public class TaskB {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue<Integer> queue = new ArrayDeque<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "push" -> {
                    queue.offer(Integer.parseInt(parts[1]));
                    writer.write("ok\n");
                }
                case "pop" -> writer.write(queue.isEmpty() ? "error\n" : queue.poll() + "\n");
                case "front" -> writer.write(queue.isEmpty() ? "error\n" : queue.peek() + "\n");
                case "size" -> writer.write(queue.size() + "\n");
                case "clear" -> {
                    queue.clear();
                    writer.write("ok\n");
                }
                case "exit" -> {
                    writer.write("bye\n");
                    writer.flush();
                    return;
                }
            }
        }
    }
}
