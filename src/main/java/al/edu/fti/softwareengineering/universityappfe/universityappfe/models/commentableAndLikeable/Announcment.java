package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.CommentableAndLikeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Announcment extends CommentableAndLikeable {
    private Course courseField;

    private String content;
}
