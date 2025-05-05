package l2;

import com.slavacom.l2.TaskB;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTestB {

    private String runWithInput(String input) throws IOException {
        // Подмена System.in и System.out
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Запускаем main
        TaskB.main(new String[]{});

        // Возвращаем стандартные потоки
        System.setIn(originalIn);
        System.setOut(originalOut);

        return out.toString().trim();
    }

    @Test
    public void test1() throws IOException {
        String input = "20\n27 6 63 28 65 11 10 52 24 68 7 80 22 58 58 4 43 92 5 44\n4\n12 20\n1 19\n2 5\n1 10\n";
        String expectedOutput = "18\n18\n5\n10";
        assertEquals(expectedOutput, runWithInput(input));
    }
}
