package com.slavacom.l4;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;


public class TaskС {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> deque = new ArrayDeque<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");

            switch (parts[0]) {
                case "push_front" -> {
                    deque.addFirst(Integer.parseInt(parts[1]));
                    writer.write("ok\n");
                }
                case "push_back" -> {
                    deque.addLast(Integer.parseInt(parts[1]));
                    writer.write("ok\n");
                }
                case "pop_front" -> writer.write(deque.isEmpty() ? "error\n" : deque.pollFirst() + "\n");
                case "pop_back" -> writer.write(deque.isEmpty() ? "error\n" : deque.pollLast() + "\n");

                case "front" -> writer.write(deque.isEmpty() ? "error\n" : deque.peekFirst() + "\n");
                case "back" -> writer.write(deque.isEmpty() ? "error\n" : deque.peekLast() + "\n");

                case "size" -> writer.write(deque.size() + "\n");
                case "clear" -> {
                    deque.clear();
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
