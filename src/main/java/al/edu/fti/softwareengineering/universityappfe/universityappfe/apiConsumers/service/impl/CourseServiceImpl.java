package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CourseService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Course;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl extends BaseServiceImpl<Course, Long> implements CourseService {
    public CourseServiceImpl() {
        super(Course.class, Course[].class);
    }

    @Override
    public ResponseEntity<Course[]> getAllAvailableCourses(int pageNumber) {
        return this.restCaller.getExchange(endpoint.getAvailableCourses() + "/" + pageNumber, Course[].class);
    }

    @Override
    public ResponseEntity<Course[]> getAllEnrolledCourses(int pageNumber) {
        return this.restCaller.getExchange(endpoint.getEnrolledCourses() + "/" + pageNumber, Course[].class);
    }

    @Override
    public ResponseEntity<Void> enrollInACourse(Long idCourse) {
        return this.restCaller.putExchange(endpoint.getEnrollInACourse(), new HttpEntity<>(idCourse), Void.class);
    }

    @Override
    public ResponseEntity<Void> dropACourse(Long idCourse) {
        return this.restCaller.deleteExchange(endpoint.getDropACourse() + "/" + idCourse);
    }

    @Override
    public ResponseEntity<Course[]> getCoursesOfAFriend(Long idFriend, int pageNumber) {
        return this.restCaller.getExchange(endpoint.getCoursesOfAFriend() + "/" + idFriend + "/" + pageNumber, Course[].class);
    }
}
