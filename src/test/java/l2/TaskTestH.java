package l2;

import com.slavacom.l2.TaskB;
import com.slavacom.l2.TaskH;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTestH {

    private String runWithInput(String input) throws IOException {
        // Подмена System.in и System.out
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Запускаем main
        TaskH.main(new String[]{});

        // Возвращаем стандартные потоки
        System.setIn(originalIn);
        System.setOut(originalOut);

        return out.toString().trim();
    }

    @Test
    public void test1() throws IOException {
        String input = "20\n" +
                "94 67 43 20 123 96 4 40 18 36 11 40 23 1 15 98 81 5 86 79\n" +
                "3\n" +
                "g 12\n" +
                "a 1 19 -12\n" +
//                "a 2 5 123\n" +
                "g 5\n";
        String expectedOutput = "40\n111";
        assertEquals(expectedOutput, runWithInput(input));
    }
}
