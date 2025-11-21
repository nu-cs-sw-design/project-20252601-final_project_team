package domain;

import domain.api_request.ExternalAPIRequest;
import domain.asm.ASMUtil;
import domain.check.CheckChain;

public class ProjectAnalyzer {
    private CheckChain checkChain;
    private ASMUtil asmUtil;
    private ExternalAPIRequest api;
    private ReportBuilder builder;
    private String path;

    public String analyze(String path) {
        return null;
    }
}
