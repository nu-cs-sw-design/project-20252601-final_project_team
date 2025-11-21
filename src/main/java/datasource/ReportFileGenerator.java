package datasource;

import java.io.FileWriter;
import java.io.IOException;

public class ReportFileGenerator {
    private final String filePath;

    public ReportFileGenerator(String filePath) {
        this.filePath = filePath;
    }

    public void generateFile(String content) {
        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}