package domain.check;

import domain.CheckRule;
import domain.asm.ClassInfo;

public class EqualsWithoutHashCodeCheck implements CheckRule {

    @Override
    public CheckResult check(ClassInfo classInfo) {
        boolean pass = !(classInfo.hasEquals && !classInfo.hasHashCode);
        return new CheckResult("EqualsWithoutHashCode", classInfo.name, pass,
                pass ? "OK" : "equals() exists without hashCode()");
    }
}
