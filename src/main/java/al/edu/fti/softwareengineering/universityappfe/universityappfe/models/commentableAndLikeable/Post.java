package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Post extends CommentableAndLikeable {
    private String content;

    private User postedBy;

    private Comment bornByComment;

    private Course bornByCourseEnrollment;
}
