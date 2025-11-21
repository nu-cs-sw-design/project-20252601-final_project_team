package presentation;

import domain.ProjectAnalyzer;
import domain.api_request.LLMFeedback;

public class Main {
    public static void main(String[] args) {
        String projectPath = args.length > 0 ? args[0] : "./classes";

        ProjectAnalyzer analyzer = new ProjectAnalyzer(projectPath,
                new LLMFeedback("http://example.com"));

        String report = analyzer.analyze(projectPath);
        System.out.println(report);
    }
}
