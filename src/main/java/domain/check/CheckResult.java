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

}
