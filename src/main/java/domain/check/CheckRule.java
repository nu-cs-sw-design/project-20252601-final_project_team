package domain;

import domain.asm.ClassInfo;
import domain.check.CheckResult;

public interface CheckRule {
    CheckResult check(ClassInfo classInfo);
}