package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.UserInteraction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends UserInteraction {
    private String content;

    private List<Like> likesOfComment;

    private CommentableAndLikeable commentedContent;
}
