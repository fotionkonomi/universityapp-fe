package al.edu.fti.softwareengineering.universityappfe.universityappfe.models;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Notification extends BaseModel {
    private User toUser;

    private boolean seen;

    private String content;

    private String parameters;
}
