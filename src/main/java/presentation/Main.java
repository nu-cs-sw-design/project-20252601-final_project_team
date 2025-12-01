package presentation;

import config.Config;
import domain.ProjectAnalyzer;

public class Main {
    public static void main(String[] args) {
        ProjectAnalyzer analyzer = new ProjectAnalyzer(Config.PROJECT_PATH);
        System.out.println(analyzer.analyze());
    }
}
