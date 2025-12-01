package domain.check;

import domain.asm.ClassInfo;
import domain.asm.ProjectInfo;

import java.util.ArrayList;
import java.util.List;

public class CheckChain {
    private final List<ClassCheckRule> classChecks = new ArrayList<>();

    private final List<ProjectCheckRUle> projectChecks  = new ArrayList<>();

    public void addClassCheck(ClassCheckRule check) {
        classChecks.add(check);
    }

    public void addProjectCheck(ProjectCheckRUle check) {
        projectChecks.add(check);
    }

    public List<CheckResult> executeChecks(ProjectInfo pi) {
        List<CheckResult> results = new ArrayList<>();
        for (ClassInfo cls : pi.classes) {
            for (ClassCheckRule r : classChecks) {
                results.add(r.check(cls));
            }
        }
        for(ProjectCheckRUle r : projectChecks) {
            results.add(r.check(pi));
        }
        return results;
    }
}
