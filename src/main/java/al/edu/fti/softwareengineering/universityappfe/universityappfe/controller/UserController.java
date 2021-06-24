package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CourseService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.UserService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.controller.util.AuthenticationFacade;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Course;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private CourseService courseService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

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

    @GetMapping("/profile")
    public String editProfile(Model model) {
        ResponseEntity<User> loggedUserResponseEntity = this.service.loggedUser();
        User loggedUser = authenticationFacade.getAuthenticatedUser();
        model.addAttribute("user", loggedUser);

        return "signup";
    }

    @PostMapping("/profile")
    public String editUser(@ModelAttribute("user") @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getArguments() + error.getDefaultMessage()));
            return "signup";
        }
        User loggedUser = authenticationFacade.getAuthenticatedUser();
        user.setId(loggedUser.getId());
        user.setCreatedAt(loggedUser.getCreatedAt());
        user.setUpdatedAt(loggedUser.getUpdatedAt());
        this.service.save(user);

        return "redirect:/logout";
    }



}
