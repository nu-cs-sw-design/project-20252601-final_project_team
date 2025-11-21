package domain.asm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MethodInfo {
    public String name;
    public String description;
    public List<String> parameters;
    public String returnType;
    public int access;
    public boolean isConstructor;
    public int complexity;
}
