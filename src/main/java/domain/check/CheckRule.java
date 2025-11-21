package domain.check;

import domain.asm.ClassInfo;

public interface CheckRule {
    public CheckResult check(ClassInfo classInfo);
}
