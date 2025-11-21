package domain.asm;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldInfo {
    public String name;
    public String description;
    public int access;
    public boolean used;
}
