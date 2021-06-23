package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CourseService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.UserService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private CourseService courseService;

    @GetMapping("/friends/{pageNumber}")
    public String friends(@PathVariable("pageNumber") int pageNumber, Model model) {
        ResponseEntity<User[]> usersResponseEntity = this.service.friendsOfLoggedUser(pageNumber);
        List<User> friendList = Arrays.asList(usersResponseEntity.getBody());
        model.addAttribute("friends", Arrays.asList(usersResponseEntity.getBody()));
        if(pageNumber > 1) {
            model.addAttribute("previousPage", pageNumber - 1);
        }
        if(friendList.size() == 10) {
            model.addAttribute("nextPage", pageNumber + 1);
        }
        return "friendship/friends";
    }

    @GetMapping("/{idFriend}/{pageNumber}")
    public String friend(Model model, @PathVariable("idFriend") Long idFriend, @PathVariable("pageNumber") int pageNumber) {
        ResponseEntity<Course[]> coursesOfAFriend = this.courseService.getCoursesOfAFriend(idFriend, pageNumber);
        List<Course> courses = Arrays.asList(coursesOfAFriend.getBody());
        ResponseEntity<User> userResponseEntity = this.service.findById(idFriend);
        User user = userResponseEntity.getBody();
        model.addAttribute("courses", Arrays.asList(coursesOfAFriend.getBody()));
        model.addAttribute("user", user);
        if(pageNumber > 1) {
            model.addAttribute("previousPage", pageNumber - 1);
        }
        if(courses.size() == 10) {
            model.addAttribute("nextPage", pageNumber + 1);
        }
        return "user/user-details";
    }

}
