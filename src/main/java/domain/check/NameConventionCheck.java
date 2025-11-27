package domain.check;

import domain.CheckRule;
import domain.asm.ClassInfo;
import domain.asm.FieldInfo;
import domain.asm.MethodInfo;

public class NameConventionCheck implements CheckRule {

    @Override
    public CheckResult check(ClassInfo classInfo) {

        StringBuilder sb = new StringBuilder();
        boolean pass = true;

        if (!checkClassName(classInfo.name)) {
            pass = false;
            sb.append("Class name must be PascalCase. ");
        }

        for (MethodInfo m : classInfo.methods) {
            if (!checkMethodName(m.name)) {
                pass = false;
                sb.append("Method '" + m.name + "' must be camelCase. ");
            }
        }

        for (FieldInfo f : classInfo.fields) {
            if (!checkFieldName(f.name, f.access)) {
                pass = false;
                sb.append("Field '" + f.name + "' must be camelCase or ALL_CAPS for constants. ");
            }
        }

        String msg = pass ? "OK" : sb.toString();
        return new CheckResult("NameConventionCheck", classInfo.name, pass, msg);
    }

    private boolean checkClassName(String name) {
        // PascalCase: First letter uppercase + not containing underscores
        return Character.isUpperCase(name.charAt(0)) && !name.contains("_");
    }

    private boolean checkMethodName(String name) {
        // camelCase: first letter lowercase + no underscores
        return Character.isLowerCase(name.charAt(0)) && !name.contains("_");
    }

    private boolean checkFieldName(String name, int access) {
        // CONSTANT: static final → ALL_CAPS
        boolean isConstant = isStaticFinal(access);

        if (isConstant) {
            return name.equals(name.toUpperCase()) && name.contains("_");
        } else {
            // normal fields must be camelCase
            return Character.isLowerCase(name.charAt(0)) && !name.contains("_");
        }
    }

    private boolean isStaticFinal(int access) {
        int ACC_STATIC = 0x0008;
        int ACC_FINAL = 0x0010;
        return (access & ACC_STATIC) != 0 && (access & ACC_FINAL) != 0;
    }
}
