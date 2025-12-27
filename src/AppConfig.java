import java.util.ArrayList;
import java.util.List;
/**
 * Класс для хранения конфигурации программы.
 * Содержит настройки, полученные из аргументов командной строки.
 */
public class AppConfig {
    private String outputPath = ".";
    private String filePrefix = "";
    private boolean appendMode = false;
    private boolean shortStats = false;
    private boolean fullStats = false;
    private List<String> inputFiles = new ArrayList<>();

    // Геттеры
    public String getOutputPath() { return outputPath; }
    public String getFilePrefix() { return filePrefix; }
    public boolean isAppendMode() { return appendMode; }
    public boolean isShortStats() { return shortStats; }
    public boolean isFullStats() { return fullStats; }
    public List<String> getInputFiles() { return inputFiles; }

    // Сеттеры
    public void setOutputPath(String path) { this.outputPath = path; }
    public void setFilePrefix(String prefix) { this.filePrefix = prefix; }
    public void setAppendMode(boolean append) { this.appendMode = append; }
    public void setShortStats(boolean shortStats) { this.shortStats = shortStats; }
    public void setFullStats(boolean fullStats) { this.fullStats = fullStats; }
}