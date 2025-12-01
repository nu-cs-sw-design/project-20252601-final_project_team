package domain.check;

import domain.asm.ClassInfo;

public class PublicConstructorClassCheck implements ClassCheckRule {

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