public class ArgumentParser {
    /**
     * Парсит аргументы командной строки и возвращает конфигурацию.
     *
     * @param args аргументы командной строки
     * @return объект AppConfig с настройками
     * @throws IllegalArgumentException если аргументы некорректны
     */
    public AppConfig parse(String[] args) throws IllegalArgumentException {
        AppConfig config = new AppConfig();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];

            switch (arg) {
                case "-o":
                    if (i + 1 >= args.length) throw new IllegalArgumentException("Отсутствует путь для -o");
                    config.setOutputPath(args[++i]);
                    break;
                case "-p":
                    if (i + 1 >= args.length) throw new IllegalArgumentException("Отсутствует префикс для -p");
                    config.setFilePrefix(args[++i]);
                    break;
                case "-a":
                    config.setAppendMode(true);
                    break;
                case "-s":
                    config.setShortStats(true);
                    break;
                case "-f":
                    config.setFullStats(true);
                    break;
                default:
                    if (!arg.startsWith("-")) {
                        config.getInputFiles().add(arg);
                    } else {
                        throw new IllegalArgumentException("Неизвестная опция: " + arg);
                    }
            }
        }

        // Проверки
        if (config.getInputFiles().isEmpty()) {
            throw new IllegalArgumentException("Не указаны входные файлы");
        }
        if (config.isShortStats() && config.isFullStats()) {
            throw new IllegalArgumentException("Опции -s и -f нельзя использовать вместе");
        }

        return config;
    }
}