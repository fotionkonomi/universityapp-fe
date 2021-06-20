package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.Hall;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.enums.CourseRepeatType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Course extends CommentableAndLikeable {
    private String name;

    private String description;

    private Hall hall;

    private Date startDateTime;

    private int repeatCount;

    private CourseRepeatType repeatType;

}
