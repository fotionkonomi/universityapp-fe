package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common;

import lombok.Data;

@Data
public abstract class SoftDeletion extends BaseModel{
    private Boolean deleted;
}
