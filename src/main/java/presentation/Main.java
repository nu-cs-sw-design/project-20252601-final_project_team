package presentation;

import config.Config;
import domain.ProjectAnalyzer;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ROOT PATH of the Java project:");
        String rootPath = scanner.nextLine().trim();
        // 1. 自动检测编译目录
        String bytecodePath = detectBytecodePath(rootPath);
        if (bytecodePath == null) {
            System.err.println("ERROR: Cannot find compiled .class files under the project root.");
            System.err.println("Please ensure you have run: `mvn package` or compiled via IntelliJ.");
            return;
        }
        System.out.println("Detected bytecode path:");
        System.out.println("  → " + bytecodePath);

        // 2. 设置到 config（如果你确实需要）
        Config.PROJECT_PATH = bytecodePath;

        // 3. 执行分析
        ProjectAnalyzer analyzer = new ProjectAnalyzer(bytecodePath);
        String report = analyzer.analyze();

        // 4. 输出结果
        System.out.println("\n===== Static Analysis Report =====");
        System.out.println(report);
        System.out.println("==================================");

        System.out.println("Report saved to: report.txt");
    }

    /**
     * 自动检测项目的 class 文件存放路径。
     * 支持 Maven 和 IntelliJ 默认目录。
     */
    private static String detectBytecodePath(String root) {

        String[] candidates = new String[] {
                root + "/target/classes",                 // Maven
                root + "/out/production",                 // IntelliJ
                root + "/out/production/classes",
                root + "/out/production/project",
                root + "/bin",                            // Eclipse
                root                                       // 用户传的如果就是 class 目录
        };

        for (String path : candidates) {
            File f = new File(path);
            if (f.exists() && f.isDirectory() && containsClassFiles(f)) {
                return f.getAbsolutePath();
            }
        }

        return null;
    }

    /**
     * 检查目录是否包含 .class 文件
     */
    private static boolean containsClassFiles(File dir) {
        File[] files = dir.listFiles();
        if (files == null) return false;

        for (File f : files) {
            if (f.isDirectory()) {
                if (containsClassFiles(f)) return true;
            }
            if (f.getName().endsWith(".class")) {
                return true;
            }
        }
        return false;
    }
}