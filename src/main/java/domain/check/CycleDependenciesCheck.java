package domain.check;

import domain.CheckRule;
import domain.asm.ClassInfo;

public class CycleDependenciesCheck implements CheckRule {
    public CycleDependenciesCheck() {}
    public CheckResult check(ClassInfo classInfo) {
        return null;
    }
}
