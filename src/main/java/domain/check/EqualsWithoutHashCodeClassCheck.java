package domain.check;

import domain.asm.ClassInfo;

public class EqualsWithoutHashCodeClassCheck implements ClassCheckRule {
    @Override
    public CheckResult check(ClassInfo classInfo) {
        boolean pass = !(classInfo.hasEquals && !classInfo.hasHashCode);
        return new CheckResult("EqualsWithoutHashCode", classInfo.name, pass,
                pass ? "OK" : "equals() exists without hashCode()");
    }
}
