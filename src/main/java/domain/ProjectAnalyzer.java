package domain;

import domain.asm.ASMUtil;
import domain.asm.ProjectInfo;
import domain.check.*;

import java.io.IOException;
import java.util.List;

public class ProjectAnalyzer {
    private final CheckChain checkChain;
    private final ASMUtil asmUtil;
    private final ReportBuilder builder;

    public ProjectAnalyzer(String path) {
        try {
            this.asmUtil = new ASMUtil(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.checkChain = new CheckChain();
        this.builder = new ReportBuilder("report.txt");

        // add checks
        checkChain.addClassCheck(new EqualsWithoutHashCodeClassCheck());
        checkChain.addClassCheck(new PublicConstructorClassCheck());
        checkChain.addClassCheck(new NameConventionClassCheck());
        checkChain.addClassCheck(new PoorCohesionClassCheck());
        checkChain.addProjectCheck(new CycleDependenciesCheck());
    }

    public String analyze() {
        ProjectInfo pi = asmUtil.loadProject();
        List<CheckResult> results = checkChain.executeChecks(pi);
        for (CheckResult r : results) builder.addResult(r);
        return builder.build(true);
    }
}
