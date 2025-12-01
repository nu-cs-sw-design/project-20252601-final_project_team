package domain.asm;

import org.objectweb.asm.ClassReader;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/*
 * TODO: Change the input of ASMUtil from string to byte[]
 */
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

        // dependencies / equals / hashcode remain TODO
        info.setDependencies(new ArrayList<>());
        info.setHasPublicConstructor(false);
        info.setHasEquals(false);
        info.setHasHashCode(false);

        return info;
    }

    /*
     * TODO: Load many classes at once
     */
    public ProjectInfo loadProject() {
        // 用于保存整个项目中所有解析出来的类信息
        List<ClassInfo> classes = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(root)) {
            paths.filter(path -> path.toString().endsWith(".class"))// 过滤，只保留 .class 文件
                    .forEach(classFile -> {
                        try {
                            // 读取 .class 文件的全部字节，获得字节码, 调用 ASM 解析此类并且加入到类的合集中
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
