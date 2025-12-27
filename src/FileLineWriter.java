import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLineWriter {
    private boolean appendMode;

    public FileLineWriter(boolean appendMode) {
        this.appendMode = appendMode;
    }

    public boolean writeFile(String filePath, String content) {
        if (content == null || content.isEmpty()) {
            return false;
        }

        try {
            Path path = Paths.get(filePath);
            Path parentDir = path.getParent();

            // Создаем папки, если нужно
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Создана папка: " + parentDir);
            }

            // Режим записи
            String mode = appendMode ? "добавление" : "перезапись";

            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter(filePath, appendMode))) {
                writer.write(content);
                if (!content.endsWith("\n")) {
                    writer.newLine();
                }
                System.out.println("Файл " + path.getFileName() + " записан (" + mode + ")");
                return true;
            }

        } catch (IOException e) {
            System.err.println("ОШИБКА записи в файл " + filePath);
            System.err.println("Детали: " + e.getMessage());
            return false;
        }
    }
}