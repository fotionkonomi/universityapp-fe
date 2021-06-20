package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import org.springframework.http.ResponseEntity;


public interface UserService extends BaseService<User, Long> {
    ResponseEntity<User[]> getUsersEnrolledInACourse(Long idCourse);
}
