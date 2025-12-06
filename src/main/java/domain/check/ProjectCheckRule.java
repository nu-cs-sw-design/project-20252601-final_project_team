package domain.check;

import domain.asm.ProjectInfo;

public interface ProjectCheckRule {
    CheckResult check(ProjectInfo projectInfo);
}
