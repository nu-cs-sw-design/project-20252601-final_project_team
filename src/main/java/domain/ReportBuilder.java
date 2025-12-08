package domain;

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

    public String build() {
        StringBuilder sb = new StringBuilder();

        for (CheckResult r : results) {
            sb.append(r.toString()).append(System.lineSeparator());
        }

        fileGenerator.generateFile(sb.toString());
        return sb.toString();
    }
}
