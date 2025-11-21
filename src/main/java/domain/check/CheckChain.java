package domain.check;

import com.sun.tools.javac.comp.Check;

import java.util.ArrayList;
import java.util.List;

public class CheckChain {
    private List<CheckRule> checks;

    public CheckChain() {
        checks = new ArrayList<>();
    }

    public void addChecks(CheckRule checks) {

    }

    public List<CheckResult> executeChecks(List<CheckRule> checks) {
        return null;
    }
}
