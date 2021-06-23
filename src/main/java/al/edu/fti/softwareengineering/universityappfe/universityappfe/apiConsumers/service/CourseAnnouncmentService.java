package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Announcment;
import org.springframework.http.ResponseEntity;

public interface CourseAnnouncmentService extends BaseService<Announcment, Long> {

    ResponseEntity<Announcment[]> getCourseAnnouncmentsOfACourse(Long idCourse);
}
