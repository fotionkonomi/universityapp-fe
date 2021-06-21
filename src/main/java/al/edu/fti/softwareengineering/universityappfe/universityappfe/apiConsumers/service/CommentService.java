package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.ContentWrapper;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions.Comment;
import org.springframework.http.ResponseEntity;

public interface CommentService extends BaseService<Comment, Long> {

    ResponseEntity<Comment[]> getAllCommentsInAContent(Long idContent);

    ResponseEntity<Void> addCommentInAContent(Long idContent, ContentWrapper contentWrapper);

    ResponseEntity<Boolean> findIfACommentIsAlreadyLiked(Long idComment);
}
