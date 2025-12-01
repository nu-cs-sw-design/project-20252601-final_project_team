package domain.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.Type;

import java.util.ArrayList;
import java.util.List;

public class ClassVisitorImpl extends ClassVisitor {
    public List<MethodInfo> methods;
    public List<FieldInfo> fields;

    public ClassVisitorImpl() {
        super(Opcodes.ASM9);
        this.methods = new ArrayList<>();
        this.fields = new ArrayList<>();
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
        MethodInfo methodInfo = new MethodInfo(
                name,
                descriptor,
                params,
                returnType,
                access,
                isConstructor
        );
        methods.add(methodInfo);
        return super.visitMethod(access, name, descriptor, signature, exceptions);
    }

    @Override
    public FieldVisitor visitField(int access, String name, String descriptor,
                                   String signature, Object value) {
        /*
         * TODO: The field used should be adapted according to the situation
         */
        FieldInfo fieldInfo = new FieldInfo(name, descriptor, access, false);
        fields.add(fieldInfo);
        return super.visitField(access, name, descriptor, signature, value);
    }
}
