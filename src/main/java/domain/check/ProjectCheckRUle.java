package domain.check;

import domain.asm.ProjectInfo;

public interface ProjectCheckRUle {
    CheckResult check(ProjectInfo projectInfo);
}
