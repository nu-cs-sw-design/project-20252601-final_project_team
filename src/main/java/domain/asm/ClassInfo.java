package domain.asm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClassInfo {
    public String name;
    public List<MethodInfo> methods;
    public List<FieldInfo> fields;
    public List<String> dependencies;
    public boolean hasPublicConstructor;
    public boolean hasEquals;
    public boolean hasHashCode;
}
