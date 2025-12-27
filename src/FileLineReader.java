import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FileLineReader {
    public List<String> readAllFiles(List<String> filePaths) {
        List<String> allLines = new ArrayList<>();

        for (String currentFilePath : filePaths) {
            Path path = Paths.get(currentFilePath);

            // Проверяем существование файла
            if (!Files.exists(path)) {
                System.err.println("Файл не найден: " + currentFilePath);
                continue;
            }

            // Проверяем, что это файл, а не папка
            if (!Files.isRegularFile(path)) {
                System.err.println("Это не файл: " + currentFilePath);
                continue;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(currentFilePath))) {
                String currentLine;
                int lineCount = 0;
                while ((currentLine = reader.readLine()) != null) {
                    allLines.add(currentLine);
                    lineCount++;
                }
                System.out.println("Прочитано " + lineCount + " строк из " + currentFilePath);

            } catch (IOException e) {
                System.err.println("Ошибка чтения файла: " + currentFilePath);
                System.err.println("Причина: " + e.getMessage());
            }
        }

        return allLines;
    }
}