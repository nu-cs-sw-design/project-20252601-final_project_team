package domain.check;

import domain.asm.ClassInfo;
import domain.asm.ProjectInfo;

import java.util.ArrayList;
import java.util.List;

public class CheckChain {
    private final List<CheckRule> checks = new ArrayList<>();

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
