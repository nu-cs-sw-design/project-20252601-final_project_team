package domain.check;

import domain.asm.ClassInfo;
import domain.asm.MethodInfo;

import java.util.List;

public class PoorCohesionCheck implements CheckRule {
    public CheckResult check(ClassInfo classInfo) {
        List<MethodInfo> methods = classInfo.methods;
        int numOfPublicMethods = 0;
        CheckResult checkResult;
        for(MethodInfo mi : methods) {
            numOfPublicMethods += mi.access == 1 ? 0 : 1;
        }
        if(numOfPublicMethods > 5) {
            checkResult = new CheckResult("Cohesion Check", classInfo.name, false, "Poor Cohesion");
        } else {
            checkResult = new CheckResult("Cohesion Check", classInfo.name, true, "High Cohesion");
        }
        return checkResult;
    }
}
