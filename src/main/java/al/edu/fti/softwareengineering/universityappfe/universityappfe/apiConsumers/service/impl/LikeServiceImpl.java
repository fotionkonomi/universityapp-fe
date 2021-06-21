package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.LikeService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions.Like;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl extends BaseServiceImpl<Like, Long> implements LikeService {

    public LikeServiceImpl() {
        super(Like.class, Like[].class);
    }

    @Override
    public ResponseEntity<Void> toggleLikeAComment(Long idComment) {
        return restCaller.postExchange(endpoint.getLikeAComment(), new HttpEntity<>(idComment), Void.class);
    }

    @Override
    public ResponseEntity<Like[]> getLikesOfAComment(Long idComment) {
        return restCaller.getExchange(endpoint.getLikeAComment() + "/" + idComment, Like[].class);
    }
}
