package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CourseAnnouncmentService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Announcment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CourseAnnouncmentServiceImpl extends BaseServiceImpl<Announcment, Long> implements CourseAnnouncmentService {
    public CourseAnnouncmentServiceImpl() {
        super(Announcment.class, Announcment[].class);
    }

    @Override
    public ResponseEntity<Announcment[]> getCourseAnnouncmentsOfACourse(Long idCourse) {
        return this.restCaller.getExchange(endpoint.getAnnouncementsForACourse() + "/" + idCourse, Announcment[].class);
    }
}
