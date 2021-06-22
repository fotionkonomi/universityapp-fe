package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.FriendshipService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.Friendship;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FriendshipServiceImpl extends BaseServiceImpl<Friendship, Long> implements FriendshipService {
    public FriendshipServiceImpl() {
        super(Friendship.class, Friendship[].class);
    }

    @Override
    public ResponseEntity<Friendship> findIfFriendshipExists(Long idPossibleFriend) {
        return restCaller.getExchange(endpoint.getDoesFriendshipExist() + "/" + idPossibleFriend, Friendship.class);
    }

    @Override
    public ResponseEntity<Void> sendFriendRequest(Long idRequestedTo) {
        return restCaller.postExchange(endpoint.getSendFriendRequest(), new HttpEntity<>(idRequestedTo), Void.class);
    }

    @Override
    public ResponseEntity<Void> acceptFriendRequest(Long idRequestedBy) {
        return restCaller.putExchange(endpoint.getAcceptFriendRequest(), new HttpEntity<>(idRequestedBy), Void.class);
    }

    @Override
    public ResponseEntity<Void> declineFriendRequest(Long idRequestedBy) {
        return restCaller.deleteExchange(endpoint.getDeclineFriendRequest() + "/" + idRequestedBy);
    }

    @Override
    public ResponseEntity<Friendship[]> friendRequestsOfLoggedUser() {
        return restCaller.getExchange(endpoint.getFriendRequestsOfLoggedUser(), Friendship[].class);
    }
}
