package al.edu.fti.softwareengineering.universityappfe.universityappfe.models;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.SoftDeletion;
import lombok.Data;

@Data
public class Hall extends SoftDeletion {
    private String name;

    private Double latitude;

    private Double longitude;

    private String description;
}
