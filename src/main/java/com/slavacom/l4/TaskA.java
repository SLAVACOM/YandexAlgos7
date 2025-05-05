package com.slavacom.l4;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;


public class TaskA {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Integer> stack = new ArrayDeque<>();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");


            switch (parts[0]) {
                case "push" -> {
                    stack.push(Integer.parseInt(parts[1]));
                    writer.write("ok\n");
                }
                case "pop" -> writer.write( stack.isEmpty() ? "error\n" : stack.pop() + "\n");
                case "size" -> writer.write(stack.size() + "\n");
                case "back" -> writer.write(stack.isEmpty() ? "error\n" : stack.peek() + "\n");
                case "clear" -> {
                    stack.clear();
                    writer.write("ok\n");
                }
                case "exit" -> {
                    writer.write("bye\n");
                    writer.flush();
                    writer.close();
                    return;
                }
            }

        }


    }
}
