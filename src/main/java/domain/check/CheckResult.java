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
        return (this.checkName == null ? "" : "Check: " + this.checkName + System.lineSeparator())
                + (this.className == null ? "" : "   Class: " + this.className + System.lineSeparator())
                + "   Result: " + (this.result ? "PASS" : "FAIL")
                + System.lineSeparator()
                + "   Message: " + this.message
                + System.lineSeparator();
    }

}
