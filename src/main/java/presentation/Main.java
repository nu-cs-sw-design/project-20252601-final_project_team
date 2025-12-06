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
        String bytecodePath = detectBytecodePath(rootPath);
        if (bytecodePath == null) {
            System.err.println("ERROR: Cannot find compiled .class files under the project root.");
            System.err.println("Please ensure you have run: `mvn package` or compiled via IntelliJ.");
            return;
        }
        System.out.println("Detected bytecode path:");
        System.out.println("  → " + bytecodePath);

        Config.PROJECT_PATH = bytecodePath;

        ProjectAnalyzer analyzer = new ProjectAnalyzer(bytecodePath, Config.FILE_GENERATION_PATH);
        String report = analyzer.analyze();

        System.out.println("\n===== Analysis Report =====");
        System.out.println(report);
        System.out.println("Report saved to: report.txt");
    }

    private static String detectBytecodePath(String root) {

        String[] candidates = new String[] {
                root + "/target/classes",
                root + "/out/production",
                root + "/out/production/classes",
                root + "/out/production/project",
                root + "/bin",
                root
        };

        for (String path : candidates) {
            File f = new File(path);
            if (f.exists() && f.isDirectory() && containsClassFiles(f)) {
                return f.getAbsolutePath();
            }
        }

        return null;
    }

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