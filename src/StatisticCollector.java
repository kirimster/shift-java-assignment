import java.util.ArrayList;
import java.util.List;

public class StatisticCollector {
    private int count = 0;

    // Для целых чисел
    private long minInt = Long.MAX_VALUE;
    private long maxInt = Long.MIN_VALUE;
    private long sumInt = 0;
    private List<Long> intValues = new ArrayList<>();

    // Для вещественных чисел
    private double minFloat = Double.MAX_VALUE;
    private double maxFloat = Double.MIN_VALUE;
    private double sumFloat = 0.0;
    private List<Double> floatValues = new ArrayList<>();

    // Для строк
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = 0;
    private List<String> stringValues = new ArrayList<>();

    // === МЕТОДЫ ДОБАВЛЕНИЯ ===

    public void addInteger(String value) {
        try {
            long num = Long.parseLong(value.trim());
            count++;
            intValues.add(num);
            minInt = Math.min(minInt, num);
            maxInt = Math.max(maxInt, num);
            sumInt += num;
        } catch (NumberFormatException e) {
            // Игнорируем, не должно происходить
        }
    }

    public void addFloat(String value) {
        try {
            double num = Double.parseDouble(value.trim());
            count++;
            floatValues.add(num);
            minFloat = Math.min(minFloat, num);
            maxFloat = Math.max(maxFloat, num);
            sumFloat += num;
        } catch (NumberFormatException e) {
            // Игнорируем
        }
    }

    public void addString(String value) {
        count++;
        stringValues.add(value);
        int len = value.length();
        minLength = Math.min(minLength, len);
        maxLength = Math.max(maxLength, len);
    }

    // === МЕТОДЫ ПОЛУЧЕНИЯ СТАТИСТИКИ ===

    public String getShortStats(String dataType) {
        switch (dataType.toLowerCase()) {
            case "integer": return "Количество: " + intValues.size();
            case "float": return "Количество: " + floatValues.size();
            case "string": return "Количество: " + stringValues.size();
            default: return "Нет данных";
        }
    }

    public String getFullStats(String dataType) {
        switch (dataType.toLowerCase()) {
            case "integer":
                if (intValues.isEmpty()) return "Нет данных";
                double avgInt = intValues.size() > 0 ? (double) sumInt / intValues.size() : 0;
                return String.format(
                        "Количество: %d, Min: %d, Max: %d, Sum: %d, Average: %.2f",
                        intValues.size(), minInt, maxInt, sumInt, avgInt
                );

            case "float":
                if (floatValues.isEmpty()) return "Нет данных";
                double avgFloat = floatValues.size() > 0 ? sumFloat / floatValues.size() : 0;
                return String.format(
                        "Количество: %d, Min: %.4f, Max: %.4f, Sum: %.4f, Average: %.4f",
                        floatValues.size(), minFloat, maxFloat, sumFloat, avgFloat
                );

            case "string":
                if (stringValues.isEmpty()) return "Нет данных";
                return String.format(
                        "Количество: %d, Min длина: %d, Max длина: %d",
                        stringValues.size(), minLength, maxLength
                );

            default:
                return "Неизвестный тип данных";
        }
    }

    // Геттеры для DataProcessor
    public int getCount() { return count; }
    public List<Long> getIntValues() { return intValues; }
    public List<Double> getFloatValues() { return floatValues; }
    public List<String> getStringValues() { return stringValues; }
}