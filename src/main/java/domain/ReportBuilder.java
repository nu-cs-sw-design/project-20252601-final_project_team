package domain;

import datasource.ReportFileGenerator;
import domain.check.CheckResult;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {
    private final List<CheckResult> results = new ArrayList<>();
    private final ReportFileGenerator fileGenerator;

    public ReportBuilder(String filePath) {
        this.fileGenerator = new ReportFileGenerator(filePath);
    }

    public void addResult(CheckResult r) {
        results.add(r);
    }

    public String build(boolean saveFile) {
        StringBuilder sb = new StringBuilder();

        for (CheckResult r : results) {
            sb.append(r.toString()).append(System.lineSeparator());
        }

        if (saveFile) fileGenerator.generateFile(sb.toString());
        return sb.toString();
    }
}
