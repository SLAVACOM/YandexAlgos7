import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    private String runWithInput(String input) throws IOException {
        // Подмена System.in и System.out
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;

        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setIn(in);
        System.setOut(new PrintStream(out));

        // Запускаем main
//        TaskS.main(new String[]{});

        // Возвращаем стандартные потоки
        System.setIn(originalIn);
        System.setOut(originalOut);

        return out.toString().trim();
    }

    @Test
    public void test1() throws IOException {
        String input = "3\nSD\nSSS\nDDS\n";
        String expectedOutput = "3";
        assertEquals(expectedOutput, runWithInput(input));
    }
}
