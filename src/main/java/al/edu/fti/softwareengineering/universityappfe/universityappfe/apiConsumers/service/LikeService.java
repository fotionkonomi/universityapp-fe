package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions.Like;
import org.springframework.http.ResponseEntity;

public interface LikeService extends BaseService<Like, Long> {
    ResponseEntity<Void> toggleLikeAComment(Long idComment);

    ResponseEntity<Like[]> getLikesOfAComment(Long idComment);
}
