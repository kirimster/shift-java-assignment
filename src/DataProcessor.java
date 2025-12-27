import java.util.*;

public class DataProcessor {
    // Коллекции для хранения данных разных типов
    private final AppConfig config;
    private List<String> integers = new ArrayList<>();
    private List<String> floats = new ArrayList<>();
    private List<String> strings = new ArrayList<>();

    private final StatisticCollector intStats = new StatisticCollector();
    private final StatisticCollector floatStats = new StatisticCollector();
    private final StatisticCollector stringStats = new StatisticCollector();

    public DataProcessor(AppConfig config) {
        this.config = config;
    }
    /**
     * Основной метод обработки данных.
     * Выполняет три этапа: классификация → запись → статистика.
     */
    public void process(List<String> allLines) {
        classifyLines(allLines);
        writeOutputFiles();
        printStatistics();
    }

    private void classifyLines(List<String> lines) {
        for (String line : lines) {
            String type = DataTypeDetector.detectType(line);
            switch (type) {
                case "int":
                    integers.add(line);
                    intStats.addInteger(line);
                    break;
                case "float":
                    floats.add(line);
                    floatStats.addFloat(line);
                    break;
                case "string":
                    strings.add(line);
                    stringStats.addString(line);
                    break;
            }
        }
    }

    private void writeOutputFiles() {
        FileLineWriter writer = new FileLineWriter(config.isAppendMode());
        int filesWritten = 0;

        if (!integers.isEmpty()) {
            String fileName = buildFilePath("integers.txt");
            if (writer.writeFile(fileName, String.join("\n", integers))) {
                filesWritten++;
            }
        }

        if (!floats.isEmpty()) {
            String fileName = buildFilePath("floats.txt");
            if (writer.writeFile(fileName, String.join("\n", floats))) {
                filesWritten++;
            }
        }

        if (!strings.isEmpty()) {
            String fileName = buildFilePath("strings.txt");
            if (writer.writeFile(fileName, String.join("\n", strings))) {
                filesWritten++;
            }
        }

        System.out.println("Создано файлов: " + filesWritten + " из 3 возможных");
    }

    private String buildFilePath(String suffix) {
        String path = config.getOutputPath();
        String prefix = config.getFilePrefix();

        // Если путь пустой или текущая директория
        if (path.equals(".") || path.isEmpty()) {
            return prefix + suffix;
        }

        // Добавляем разделитель, если нужно
        if (!path.endsWith("/") && !path.endsWith("\\")) {
            path = path + "/";
        }

        return path + prefix + suffix;
    }

    private void printStatistics() {
        System.out.println("\n=== СТАТИСТИКА ===");

        if (config.isShortStats() || config.isFullStats()) {
            // Если указана какая-либо опция статистики
            if (config.isShortStats()) {
                System.out.println("Целые числа: " + intStats.getShortStats("integer"));
                System.out.println("Вещественные: " + floatStats.getShortStats("float"));
                System.out.println("Строки: " + stringStats.getShortStats("string"));
            } else if (config.isFullStats()) {
                System.out.println("Целые числа: " + intStats.getFullStats("integer"));
                System.out.println("Вещественные: " + floatStats.getFullStats("float"));
                System.out.println("Строки: " + stringStats.getFullStats("string"));
            }
        } else {
            // Если не указаны -s или -f, выводим только количество
            System.out.println("Целые числа: " + intStats.getCount() + " элементов");
            System.out.println("Вещественные: " + floatStats.getCount() + " элементов");
            System.out.println("Строки: " + stringStats.getCount() + " элементов");
        }
    }
}