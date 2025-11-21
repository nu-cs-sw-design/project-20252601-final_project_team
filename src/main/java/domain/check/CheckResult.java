package domain.check;

public class CheckResult {
    public String checkName;
    public String className;
    public boolean result;
    public String message;

    public CheckResult(String checkName, String className, boolean result, String message) {
        this.checkName = checkName;
        this.className = className;
        this.result = result;
        this.message = message;
    }
}
