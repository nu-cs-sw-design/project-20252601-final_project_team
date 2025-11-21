package domain;

import datasource.ReportFileGenerator;
import domain.check.CheckResult;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {
    private List<CheckResult> results = new ArrayList<>();
    private ReportFileGenerator fileGenerator;

    public ReportBuilder(String filePath) {
        this.fileGenerator = new ReportFileGenerator(filePath);
    }

    public void addResult(CheckResult r) {
        results.add(r);
    }

    public String build(boolean saveFile) {
        StringBuilder sb = new StringBuilder();

        for (CheckResult r : results) {
            sb.append(r.checkName).append(": ")
                    .append(r.className).append(" -> ")
                    .append(r.message).append("\n");
        }

        if (saveFile) fileGenerator.generateFile(sb.toString());
        return sb.toString();
    }
}
