package l1;

import com.slavacom.l1.TaskH;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskHTest {

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
    public void testExample1() throws IOException {
        String input = "3\nSD\nSSS\nDDS\n";
        String expectedOutput = "3";
        assertEquals(expectedOutput, runWithInput(input));
    }

    @Test
    public void test2() throws IOException {
        String input = "7\nSSDSD\nSDS\nSSD\nSDSD\nSD\nDSS\nDSDD";
        String expectedOutput = "3";
        assertEquals(expectedOutput, runWithInput(input));
    }


    @Test
    public void testOnlySimpleTasks() throws IOException {
        String input = "2\nSS\nSSS\n";
        String expectedOutput = "3"; // Вася берёт дни 0,2,4,6: все простые
        assertEquals(expectedOutput, runWithInput(input));
    }

    @Test
    public void testOnlyDifficultTasks() throws IOException {
        String input = "2\nDD\nDDD\n";
        String expectedOutput = "0"; // Вася ничего не получит (все сложные)
        assertEquals(expectedOutput, runWithInput(input));
    }

    @Test
    public void testOneOrderAlternating() throws IOException {
        String input = "1\nSDSDSD\n";
        String expectedOutput = "3"; // Вася работает в дни 0,2,4 → 3 простых
        assertEquals(expectedOutput, runWithInput(input));
    }
}
