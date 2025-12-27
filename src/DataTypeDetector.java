public class DataTypeDetector {
    /**
     * Определяет тип данных в строке.
     * Важно: проверяем сначала целые числа, потом вещественные,
     * потому что Double.parseDouble() успешно парсит целые числа.
     *
     * @param line строка для анализа
     * @return "int", "float" или "string"
     */
    public static String detectType(String line) {
        try {
            Long.parseLong(line);
            return "int";
        } catch (NumberFormatException ignored1) {
            try {
                Double.parseDouble(line);
                return "float";
            } catch (NumberFormatException ignored2) {
                return "string";
            }
        }
    }
}