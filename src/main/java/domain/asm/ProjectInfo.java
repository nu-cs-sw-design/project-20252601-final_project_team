package domain.asm;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectInfo {
    public List<ClassInfo> classes;
}
