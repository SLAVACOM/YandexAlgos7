package l3;

import com.slavacom.l3.TaskJ;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskTestJ {

    public static void main(String[] args) throws IOException {
//        runTest("abacabaca");
        runTest(generateRandomString(100000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        runTest(generateRandomString(200000));
        System.out.println("All tests passed!");
    }

    private static void runTest(String input) throws IOException {
        // Сначала PACK
        ByteArrayOutputStream packOut = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(packOut));

        ByteArrayInputStream packIn = new ByteArrayInputStream(("pack\n" + input + "\n").getBytes());
        System.setIn(packIn);

        TaskJ.main(new String[0]);

        System.setOut(originalOut);
        System.setIn(System.in);

        String[] outputLines = packOut.toString().split("\n");
        int n = Integer.parseInt(outputLines[0].trim());
        String[] byteStrings = outputLines[1].trim().split(" ");
        List<Byte> byteList = new ArrayList<>();
        for (String bs : byteStrings) {
            byteList.add((byte) Integer.parseInt(bs));
        }

        // Проверка условия на размер сжатия
        if (n > input.length() / 2) {
            throw new AssertionError("Test failed: Compressed size is too large! " +
                    "Input length = " + input.length() + ", compressed bytes = " + n);
        }

        // Теперь UNPACK
        ByteArrayOutputStream unpackOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(unpackOut));

        StringBuilder unpackInput = new StringBuilder();
        unpackInput.append("unpack\n").append(n).append("\n");
        for (byte b : byteList) {
            unpackInput.append((b & 0xFF)).append(" ");
        }
        unpackInput.append("\n");

        ByteArrayInputStream unpackIn = new ByteArrayInputStream(unpackInput.toString().getBytes());
        System.setIn(unpackIn);

        TaskJ.main(new String[0]);

        System.setOut(originalOut);
        System.setIn(System.in);

        String unpacked = unpackOut.toString().trim();

        // Проверка точности восстановления
        if (!input.equals(unpacked)) {
            throw new AssertionError("Test failed!\nExpected: " + input + "\nGot: " + unpacked);
        }
    }

    // Генерация случайной строки
    private static String generateRandomString(int length) {
        Random random = new Random(42); // фиксированное зерно для стабильности тестов
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('a' + random.nextInt(26)));
        }
        return sb.toString();
    }
}