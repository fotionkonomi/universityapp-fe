package al.edu.fti.softwareengineering.universityappfe.universityappfe.models;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ContentWrapper {

    @NotEmpty
    private String content;
}
