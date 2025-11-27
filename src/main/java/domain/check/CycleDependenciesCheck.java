package domain.check;

import domain.asm.ClassInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CycleDependenciesCheck implements CheckRule {
    private Map<String, Set<String>> dependencies;
    public CycleDependenciesCheck() {
        dependencies = new HashMap<>();
    }
    public CheckResult check(ClassInfo classInfo) {
        return null;
    }
}
