package domain.asm;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ASMUtil {
    private final Path root;

    public ASMUtil(String projectRootPath) throws IOException{
        this.root = Paths.get(projectRootPath);
    }

    public ClassInfo loadClass(byte[] bytecode) {
        ClassReader cr = new ClassReader(bytecode);
        ClassVisitorImpl cv = new ClassVisitorImpl();
        cr.accept(cv, 0);

        ClassInfo info = new ClassInfo();
        info.setName(cr.getClassName().replace('/', '.'));
        info.setMethods(cv.methods);
        info.setFields(cv.fields);
        info.setDependencies(new ArrayList<>());
        info.setHasPublicConstructor(false);
        info.setHasEquals(false);
        info.setHasHashCode(false);

        return info;
    }

    public ProjectInfo loadProject() {
        List<ClassInfo> classes = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(root)) {
            paths.filter(path -> path.toString().endsWith(".class"))
                    .forEach(classFile -> {
                        try {
                            byte[] bytecode = Files.readAllBytes(classFile);
                            ClassInfo info = loadClass(bytecode);
                            classes.add(info);
                        } catch (Exception e) {
                            throw new RuntimeException("Error reading " + classFile, e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException("Failed to scan project: " + root, e);
        }
        return new ProjectInfo(classes);
    }
}
