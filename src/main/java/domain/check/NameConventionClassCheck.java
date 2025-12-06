package domain.check;

import domain.asm.ClassInfo;
import domain.asm.FieldInfo;
import domain.asm.MethodInfo;

import java.util.HashSet;
import java.util.Set;

public class NameConventionClassCheck implements ClassCheckRule {
    /**
    * The following names do not need to be checked:
    * <init>, <clinit>;
    **/
    private Set<String> waived;

    public NameConventionClassCheck() {
        this.waived = new HashSet<>();
        waived.add("<init>");
        waived.add("<clinit>");
    }

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
                sb.append("Method '").append(m.name).append("' must be camelCase. ");
            }
        }

        for (FieldInfo f : classInfo.fields) {
            if (!checkFieldName(f.name, f.access)) {
                pass = false;
                sb.append("Field '").append(f.name).append("' must be camelCase or ALL_CAPS for constants. ");
            }
        }

        String msg = pass ? "OK" : sb.toString();
        return new CheckResult("NameConventionClassCheck", classInfo.name, pass, msg);
    }

    private boolean checkClassName(String name) {
        if(name.contains(".")) name = name.substring(name.lastIndexOf('.') + 1);
        return waived.contains(name) || Character.isUpperCase(name.charAt(0)) && !name.contains("_");
    }

    private boolean checkMethodName(String name) {
        if(name.contains(".")) name = name.substring(name.lastIndexOf('.') + 1);
        return waived.contains(name) || Character.isLowerCase(name.charAt(0)) && !name.contains("_");
    }

    private boolean checkFieldName(String name, int access) {
        if(name.contains(".")) name = name.substring(name.lastIndexOf('.') + 1);
        boolean isConstant = isStaticFinal(access);
        if (isConstant) {
            return waived.contains(name) || name.equals(name.toUpperCase()) && name.contains("_");
        } else {
            return waived.contains(name) || Character.isLowerCase(name.charAt(0)) && !name.contains("_");
        }
    }

    private boolean isStaticFinal(int access) {
        int ACC_STATIC = 0x0008;
        int ACC_FINAL = 0x0010;
        return (access & ACC_STATIC) != 0 && (access & ACC_FINAL) != 0;
    }
}
