package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.UserService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

    public UserServiceImpl() {
        super(User.class, User[].class);
    }

    @Override
    public ResponseEntity<User[]> getUsersEnrolledInACourse(Long idCourse) {
        return restCaller.getExchange(endpoint.getStudentsOfACourse() + "/" + idCourse, User[].class);
    }

    @Override
    public ResponseEntity<User[]> friendsOfLoggedUser(int pageNumber) {
        return restCaller.getExchange(endpoint.getFriendsOfLoggedUser() + "/" + pageNumber, User[].class);
    }

    @Override
    public ResponseEntity<User> loggedUser() {
        return restCaller.getExchange(endpoint.getLoggedUserProfile(), User.class);
    }
}
