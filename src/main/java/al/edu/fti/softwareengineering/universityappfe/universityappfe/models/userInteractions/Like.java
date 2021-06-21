package al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.CommentableAndLikeable;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.common.UserInteraction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Like extends UserInteraction {
    private Comment commentLiked;

    private CommentableAndLikeable likedContent;
}
