package domain.check;

import domain.CheckRule;
import domain.asm.ClassInfo;
import domain.asm.ProjectInfo;

import java.util.ArrayList;
import java.util.List;

public class CheckChain {
    private List<CheckRule> checks = new ArrayList<>();
    private ProjectInfo projectInfo;

    public void addCheck(CheckRule check) {
        checks.add(check);
    }

    public List<CheckResult> executeChecks(ProjectInfo pi) {
        List<CheckResult> results = new ArrayList<>();
        for (ClassInfo cls : pi.classes) {
            for (CheckRule r : checks) {
                results.add(r.check(cls));
            }
        }
        return results;
    }
}
