package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller.ajax;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ajax/course")
public class CourseRestController {

    @Autowired
    private CourseService courseService;

    @PutMapping("/enroll/{idCourse}")
    public void enrollInCourse(@PathVariable("idCourse") Long idCourse) {
        this.courseService.enrollInACourse(idCourse);
    }

    @DeleteMapping("/drop/{idCourse}")
    public void dropCourse(@PathVariable("idCourse") Long idCourse) {
        this.courseService.dropACourse(idCourse);
    }
}
