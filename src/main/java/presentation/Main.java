package presentation;

import config.Config;
import domain.ProjectAnalyzer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Config.PROJECT_PATH = in.nextLine();
        ProjectAnalyzer analyzer = new ProjectAnalyzer(Config.PROJECT_PATH);
        System.out.println(analyzer.analyze());
    }
}
