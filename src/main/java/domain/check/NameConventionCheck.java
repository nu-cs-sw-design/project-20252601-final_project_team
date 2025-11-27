package domain.check;

import domain.asm.ClassInfo;

public class NameConventionCheck implements CheckRule {

    @Override
    public CheckResult check(ClassInfo classInfo) {
        /*
        * TODO: Methods names
        *  TODO: Field names
         */
        boolean pass = Character.isUpperCase(classInfo.name.charAt(0));
        return new CheckResult("NameConvention", classInfo.name, pass,
                pass ? "OK" : "Class name should start with upper-case");
    }
}
