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

    public String toString() {
        return (this.checkName == null ? "" : "checkName: " + this.checkName) +
                (this.className == null ? "" : "   className: " + this.className) +
                "   result: " + this.result +
                "   message: " + this.message;
    }

}
