public class Main {
    public static void main(String[] args) {

        try {
            // 1. Парсим аргументы
            ArgumentParser parser = new ArgumentParser();
            AppConfig config = parser.parse(args);

            // 2. Читаем файлы
            FileLineReader reader = new FileLineReader();
            System.out.println("Чтение файлов: " + config.getInputFiles());
            var lines = reader.readAllFiles(config.getInputFiles());

            if (lines.isEmpty()) {
                System.out.println("Нет данных для обработки");
                return;
            }

            // 3. Обрабатываем
            DataProcessor processor = new DataProcessor(config);
            processor.process(lines);

            System.out.println("\nОбработка завершена успешно!");

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка в аргументах: " + e.getMessage());
            printHelp();
        } catch (Exception e) {
            System.err.println("Неожиданная ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void printHelp() {
        System.out.println("\nИспользование: java -jar util.jar [опции] file1.txt file2.txt ...");
        System.out.println("Опции:");
        System.out.println("  -o <путь>    выходная папка (по умолчанию: текущая)");
        System.out.println("  -p <префикс> префикс имен выходных файлов");
        System.out.println("  -a           добавлять в существующие файлы");
        System.out.println("  -s           краткая статистика");
        System.out.println("  -f           полная статистика");
        System.out.println("\nПример:");
        System.out.println("  java -jar util.jar -p result_ -s input1.txt input2.txt");
    }
}