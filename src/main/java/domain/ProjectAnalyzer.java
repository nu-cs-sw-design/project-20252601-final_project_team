package domain;

import domain.api_request.ExternalAPIRequest;
import domain.asm.ASMUtil;
import domain.asm.ProjectInfo;
import domain.check.CheckChain;
import domain.check.CheckResult;
import domain.check.EqualsWithoutHashCodeCheck;
import domain.check.PublicConstructorCheck;

import java.util.List;

public class ProjectAnalyzer {
    private CheckChain checkChain;
    private ASMUtil asmUtil;
    private ExternalAPIRequest api;
    private ReportBuilder builder;
    private String path;

    public ProjectAnalyzer(String path, ExternalAPIRequest api) {
        this.path = path;
        this.asmUtil = new ASMUtil(path);
        this.api = api;
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
