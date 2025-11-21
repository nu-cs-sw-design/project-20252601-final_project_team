package domain.asm;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.util.ArrayList;

public class ASMUtil {
    private String path;
    private final ClassReader cr;
    private final ClassVisitorImpl cv;

    public ASMUtil(String path) throws IOException {
        cr = new ClassReader(path);
        cv = new ClassVisitorImpl(new ArrayList<>());
        cr.accept(cv, 0);
        System.out.println(cv.methods);
    }
    public ClassInfo loadClass(byte[] bytecode) {
        ClassInfo classInfo = new ClassInfo();
        classInfo.setName(cr.getClassName());
        classInfo.setFields(cv.fields);
        classInfo.setMethods(cv.methods);
        return null;
    }

    public ProjectInfo loadProject() {
        return null;
    }

    public static void main(String[] args) {
        try {
            ASMUtil asmUtil = new ASMUtil("java.lang.String");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
