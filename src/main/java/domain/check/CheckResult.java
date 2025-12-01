package domain.check;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CheckResult {
    public String checkName;
    public String className;
    public boolean result;
    public String message;

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
