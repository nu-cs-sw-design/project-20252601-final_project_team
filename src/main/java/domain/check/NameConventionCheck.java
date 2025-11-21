package domain.check;

import domain.CheckRule;
import domain.asm.ClassInfo;

public class NameConventionCheck implements CheckRule {

    @Override
    public CheckResult check(ClassInfo classInfo) {
        boolean pass = Character.isUpperCase(classInfo.name.charAt(0));
        return new CheckResult("NameConvention", classInfo.name, pass,
                pass ? "OK" : "Class name should start with upper-case");
    }
}
