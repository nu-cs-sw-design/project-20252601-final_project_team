package domain.check;

import domain.asm.ProjectInfo;

import java.util.*;

public class CycleDependenciesCheck implements ProjectCheckRule {
    private final Map<String, Set<String>> dependencyGraph;

    public CycleDependenciesCheck() {
        this.dependencyGraph = new HashMap<>();
    }

    public void addDependencies(String className, List<String> dependencies) {
        this.dependencyGraph.put(className, new HashSet<>(dependencies));
    }
    public CheckResult check(ProjectInfo projectInfo) {
        Set<String> visited = new HashSet<>();
        Deque<String> stack = new ArrayDeque<>();
        Set<String> stackSet = new HashSet<>();
        Set<List<String>> allCycles = new HashSet<>();
        String checkName;
        String message;
        boolean result;
        checkName = "Cycle Dependencies Check";
        for (String cls : dependencyGraph.keySet()) {
            dfs(cls, visited, stack, stackSet, allCycles);
        }

        if (allCycles.isEmpty()) {
            message = "There is no cycle dependencies";
            result = true;
        } else {
            StringBuilder res = new StringBuilder();
            res.append("Number of cycle dependencies: ").append(allCycles.size());
            for (List<String> cycle : allCycles) {
                res.append(cycle);
            }
            message = res.toString();
            result = false;
        }
        return new CheckResult(checkName, null, result, message);
    }

    private void dfs(String cls, Set<String> visited, Deque<String> stack, Set<String> stackSet, Set<List<String>> allCycles) {
        if (stackSet.contains(cls)) {
            List<String> cycle = new ArrayList<>();
            boolean inCycle = false;
            for (String s : stack) {
                if (s.equals(cls)) inCycle = true;
                if (inCycle) cycle.add(s);
            }
            cycle.add(cls);
            allCycles.add(new ArrayList<>(cycle));
            return;
        }
        if (visited.contains(cls)) return;
        visited.add(cls);
        stack.push(cls);
        stackSet.add(cls);

        for (String dep : dependencyGraph.getOrDefault(cls, Collections.emptySet())) {
            dfs(dep, visited, stack, stackSet, allCycles);
        }

        stack.pop();
        stackSet.remove(cls);
    }
}
