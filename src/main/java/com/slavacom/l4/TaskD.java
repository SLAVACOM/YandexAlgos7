package com.slavacom.l4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskD {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine().trim());

        List<String> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = reader.readLine().trim();
            if (line.startsWith("Alt+Tab")) {
                if (list.isEmpty()) {
                    writer.newLine();
                    continue;
                }

                int tabCount = (line.length() - 3) / 4;
                int size = list.size();
                int index = (size - 1 - (tabCount % size) + size) % size;

                String appToActivate = list.get(index);
                list.remove(index);
                list.add(appToActivate);

                writer.write(appToActivate);
                writer.newLine();
            } else {
                if (line.length() > 4) {
                    String appName = line.substring(4).trim();
                    list.add(appName);
                    writer.write(appName);
                }
                writer.newLine();
            }
        }

        reader.close();
        writer.flush();
        writer.close();
    }
}