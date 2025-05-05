package com.slavacom.l3;

import java.io.*;
import java.util.*;

public class TaskF {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(reader.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        BitSet[][] orList = new BitSet[n][4];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 4; j++)
                orList[i][j] = new BitSet(n);

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(reader.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken()) - 1;

            orList[z][0].set(x);
            orList[z][1].set(y);
            orList[x][2].set(y);
            orList[y][3].set(x);
        }
        reader.close();

        BitSet full = new BitSet(n);
        full.set(0, n);

        for (int i = 0; i < n; i++) {
            if (!orList[i][0].equals(full) && !orList[i][1].equals(full)) {
                for (int j = 0; j < n; j++) {
                    if (!orList[i][0].get(j)) {
                        BitSet t = (BitSet) orList[i][1].clone();
                        t.or(orList[j][2]);
                        if (!t.equals(full)) {
                            for (int kIndex = 0; kIndex < n; kIndex++) {
                                if (!t.get(kIndex)) {
                                    BitSet temp = (BitSet) orList[i][0].clone();
                                    temp.or(orList[kIndex][3]);
                                    if (!temp.equals(full)) {
                                        writer.write("NO\n");
                                        writer.write((j + 1) + " " + (kIndex + 1) + " " + (i + 1));
                                        writer.flush();
                                        writer.close();
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        writer.write("YES");
        writer.flush();
        writer.close();

    }
}
