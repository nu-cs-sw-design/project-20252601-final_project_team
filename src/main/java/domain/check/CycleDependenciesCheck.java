package domain.check;

import com.sun.tools.javac.comp.Check;
import domain.asm.ClassInfo;

public class CycleDependenciesCheck implements CheckRule {
    public CycleDependenciesCheck() {}
    public CheckResult check(ClassInfo classInfo) {
        return null;
    }
}
