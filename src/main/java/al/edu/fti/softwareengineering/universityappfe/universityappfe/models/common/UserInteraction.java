package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import lombok.Data;

@Data
public abstract class UserInteraction extends BaseModel {
    private User interactedBy;
}
