package domain.check;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CheckResult {
    private String checkName;
    private String className;
    private boolean result;
    private String message;

    @Override
    public String toString() {
        return "----------------------------------------\n" +
                "Check:      " + checkName + "\n" +
                "Class:      " + className + "\n" +
                "Result:     " + (result ? "PASS" : "FAIL") + "\n" +
                "Message:    " + message + "\n" +
                "----------------------------------------";
    }

}
