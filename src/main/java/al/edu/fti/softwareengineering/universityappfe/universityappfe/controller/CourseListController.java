package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CommentService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CourseService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.UserService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseListController {
    @Autowired
    private CourseService service;

    @GetMapping("/available/{pageNumber}")
    public String availableCourses(Model model, @PathVariable("pageNumber") int pageNumber) {
        ResponseEntity<Course[]> courses = service.getAllAvailableCourses(pageNumber);
        List<Course> courseList = Arrays.asList(courses.getBody());
        model.addAttribute("coursesAvailable", courseList);
        if(pageNumber > 1) {
            model.addAttribute("previousPage", pageNumber - 1);
        }
        if(courseList.size() == 10) {
            model.addAttribute("nextPage", pageNumber + 1);
        }

        return "course/available-courses";
    }

    @GetMapping("/enrolled/{pageNumber}")
    public String enrolledCourses(Model model, @PathVariable("pageNumber") int pageNumber) {
        ResponseEntity<Course[]> courses = service.getAllEnrolledCourses(pageNumber);
        List<Course> courseList = Arrays.asList(courses.getBody());
        model.addAttribute("coursesEnrolled", courseList);
        if(pageNumber > 1) {
            model.addAttribute("previousPage", pageNumber - 1);
        }
        if(courseList.size() == 10) {
            model.addAttribute("nextPage", pageNumber + 1);
        }

        return "course/enrolled-courses";
    }
}
