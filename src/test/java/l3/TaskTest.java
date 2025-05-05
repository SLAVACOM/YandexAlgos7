package l3;

import com.slavacom.l3.TaskF;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest {

    public static void main(String[] args) throws Exception {
        // Указание пути к файлу для тестирования
        String inputFilePath = "l3-f-21.txt";
        String outputFilePath = "test_output.txt";
        testPerformance(inputFilePath, outputFilePath); // 1 миллион ладей
    }

    // Метод для тестирования производительности программы
    private static void testPerformance(String inputFilePath, String outputFilePath) throws Exception {
        System.out.println("Testing with input file: " + inputFilePath);

        File inputFile = new File(inputFilePath);
        if (!inputFile.exists()) {
            System.err.println("Input file does not exist!");
            return;
        }

        // Чтение содержимого входного файла
        StringBuilder input = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                input.append(line).append("\n");
            }
        }

        // Удаляем старый файл вывода
        File tempOutputFile = new File(outputFilePath);
        if (tempOutputFile.exists()) {
            tempOutputFile.delete();
        }

        // Засекаем время начала выполнения
        long startTime = System.nanoTime();

        // Запускаем процесс с вашей программой
        ProcessBuilder pb = new ProcessBuilder("java", "com.slavacom.l3.TaskF");
        pb.redirectInput(inputFile);   // Ввод из файла
        pb.redirectOutput(tempOutputFile);  // Вывод в файл

        Process process = pb.start();
        process.waitFor(); // Ожидаем завершения процесса

        // Засекаем время окончания выполнения
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000; // Время в миллисекундах

        // Вывод времени выполнения программы
        System.out.println("Execution time: " + duration + " ms");

        // Проверка времени
        if (duration > 1000) {
            System.err.println("Test failed: Execution took longer than expected!");
        } else {
            System.out.println("Test passed: Execution time is within limits.");
        }

        // Теперь читаем файл с результатом программы
        try (BufferedReader outputReader = new BufferedReader(new FileReader(tempOutputFile))) {
            String outputLine;
            StringBuilder output = new StringBuilder();
            while ((outputLine = outputReader.readLine()) != null) {
                output.append(outputLine).append("\n");
            }
            // Выводим результаты на экран
            System.out.println("Program output: ");
            System.out.println(output.toString());
        }

        // Удаляем временный файл вывода после выполнения теста
        tempOutputFile.delete();
    }
}
