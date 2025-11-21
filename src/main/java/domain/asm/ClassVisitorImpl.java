package domain.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.List;

public class ClassVisitorImpl extends ClassVisitor {
    public List<MethodInfo> methods;
    public List<FieldInfo> fields;

    public ClassVisitorImpl(List<MethodInfo> methods) {
        super(Opcodes.ASM9);
        this.methods = methods;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        Type methodType = Type.getMethodType(descriptor);
        Type[] argumentTypes = methodType.getArgumentTypes();
        List<String> params = new ArrayList<>();
        for (Type t : argumentTypes) {
            params.add(t.getClassName());
        }
        String returnType = methodType.getReturnType().getClassName();
        boolean isConstructor = "<init>".equals(name);
        /*
        * TODO: set complexity
         */
        int complexity = 0;

        MethodInfo methodInfo = new MethodInfo(
                name,
                descriptor,
                params,
                returnType,
                access,
                isConstructor,
                complexity
        );
        methods.add(methodInfo);
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor,
                                   String signature, Object value) {
        FieldInfo fieldInfo = new FieldInfo(name, descriptor, access, false);
        fields.add(fieldInfo);
        return super.visitField(access, name, descriptor, signature, value);
    }
}
