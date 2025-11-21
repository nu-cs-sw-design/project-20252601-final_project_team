package domain.check;

import domain.CheckRule;
import domain.asm.ClassInfo;

public class PublicConstructorCheck implements CheckRule {

    @Override
    public CheckResult check(ClassInfo classInfo) {
        return new CheckResult(
                "PublicConstructor",
                classInfo.name,
                classInfo.hasPublicConstructor,
                classInfo.hasPublicConstructor ? "OK" : "No public constructor"
        );
    }
}