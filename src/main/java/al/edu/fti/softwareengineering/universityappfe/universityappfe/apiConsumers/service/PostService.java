package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.ContentWrapper;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Post;
import org.springframework.http.ResponseEntity;

public interface PostService extends BaseService<Post, Long> {
    ResponseEntity<Void> addPost(ContentWrapper post);

    ResponseEntity<Post[]> postsToShowLoggedUser(int pageNumber);
}
