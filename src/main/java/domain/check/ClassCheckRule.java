package domain.check;

import domain.asm.ClassInfo;

public interface ClassCheckRule {
    CheckResult check(ClassInfo classInfo);
}