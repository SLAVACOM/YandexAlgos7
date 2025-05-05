package com.slavacom.l4;

import java.io.*;
import java.util.StringTokenizer;

public class TaskJ {
    static class Node {
        int length;
        Node prev, next;

        Node(int length) {
            this.length = length;
        }
    }

    static Node head = new Node(-1);
    static Node tail = new Node(-1);
    static Node cursor;
    static int cursorIndex = 0;
    static int size = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        head.next = tail;
        tail.prev = head;

        long total = 0;

        for (int i = 0; i < n; i++) {
            int len = Integer.parseInt(tokenizer.nextToken());
            Node node = new Node(len);
            insertBefore(tail, node);
            size++;
        }

        cursor = head.next;
        cursorIndex = 0;

        Node temp = head.next;
        while (temp != tail) {
            total += (long) temp.length * temp.length;
            temp = temp.next;
        }

        int k = Integer.parseInt(reader.readLine());
        writer.write(total + "\n");

        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int type = Integer.parseInt(tokenizer.nextToken());
            int idx = Integer.parseInt(tokenizer.nextToken()) - 1;

            if (idx < 0 || idx >= size) {
                writer.write(total + "\n");
                continue;
            }

            Node node = getNode(idx);
            if (node == null) {
                writer.write(total + "\n");
                continue;
            }

            if (type == 2) {
                if (node.length < 2) {
                    writer.write(total + "\n");
                    continue;
                }
                int len = node.length;
                int a = len / 2;
                int b = len - a;
                total -= (long) len * len;

                Node left = new Node(a);
                Node right = new Node(b);

                insertBefore(node, left);
                insertBefore(node, right);
                remove(node);
                total += (long) a * a + (long) b * b;

                cursor = left;
                cursorIndex = idx;
                size++;
            } else {
                int len = node.length;
                total -= (long) len * len;

                Node prev = node.prev != head ? node.prev : null;
                Node next = node.next != tail ? node.next : null;

                if (prev == null && next == null) {
                    writer.write(total + "\n");
                    continue;
                }

                if (prev == null) {
                    total -= (long) next.length * next.length;
                    next.length += len;
                    total += (long) next.length * next.length;
                    cursor = next;
                    cursorIndex = idx;
                } else if (next == null) {
                    total -= (long) prev.length * prev.length;
                    prev.length += len;
                    total += (long) prev.length * prev.length;
                    cursor = prev;
                    cursorIndex = idx - 1;
                } else {
                    int a = len / 2;
                    int b = len - a;

                    total -= (long) prev.length * prev.length;
                    total -= (long) next.length * next.length;

                    prev.length += a;
                    next.length += b;

                    total += (long) prev.length * prev.length;
                    total += (long) next.length * next.length;

                    cursor = next;
                    cursorIndex = idx;
                }
                remove(node);
                size--;
            }
            writer.write(total + "\n");
        }

        writer.flush();
        reader.close();
        writer.close();
    }

    static void insertBefore(Node ref, Node newNode) {
        newNode.prev = ref.prev;
        newNode.next = ref;
        ref.prev.next = newNode;
        ref.prev = newNode;
    }

    static void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    static Node getNode(int index) {
        if (index < 0 || index >= size) return null;

        while (cursorIndex < index) {
            cursor = cursor.next;
            cursorIndex++;
        }
        while (cursorIndex > index) {
            cursor = cursor.prev;
            cursorIndex--;
        }
        return cursor;
    }
}
