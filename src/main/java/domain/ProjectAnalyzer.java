package domain;

import domain.asm.ASMUtil;
import domain.asm.ProjectInfo;
import domain.check.CheckChain;
import domain.check.CheckResult;
import domain.check.EqualsWithoutHashCodeCheck;
import domain.check.PublicConstructorCheck;

import java.io.IOException;
import java.util.List;

public class ProjectAnalyzer {
    private final CheckChain checkChain;
    private final ASMUtil asmUtil;
    private ReportBuilder builder;
    private String path;

    public ProjectAnalyzer(String path) {
        this.path = path;
        try {
            this.asmUtil = new ASMUtil(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.checkChain = new CheckChain();
        this.builder = new ReportBuilder("report.txt");

        // add checks
        checkChain.addCheck(new EqualsWithoutHashCodeCheck());
        checkChain.addCheck(new PublicConstructorCheck());
    }

    public String analyze(String path) {
        ProjectInfo pi = asmUtil.loadProject();
        List<CheckResult> results = checkChain.executeChecks(pi);

        for (CheckResult r : results) builder.addResult(r);

        return builder.build(true);
    }
}
