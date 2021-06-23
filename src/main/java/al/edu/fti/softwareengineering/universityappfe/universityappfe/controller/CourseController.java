package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.*;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.ContentWrapper;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.Friendship;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Course;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Announcment;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/course/details")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService service;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FriendshipService friendshipService;

    @Autowired
    private CourseAnnouncmentService courseAnnouncmentService;

    @ModelAttribute("course")
    public Course course(@PathVariable("idCourse") Long idCourse) {
        ResponseEntity<Course> courseResponseEntity = service.findById(idCourse);
        return courseResponseEntity.getBody();
    }

    @ModelAttribute("students")
    public List<User> students(@PathVariable("idCourse") Long idCourse) {
        ResponseEntity<User[]> userResponseEntity = userService.getUsersEnrolledInACourse(idCourse);
        return Arrays.asList(userResponseEntity.getBody());
    }

    @ModelAttribute("idCourse")
    public Long idCourse(@PathVariable("idCourse") Long idCourse) {
        return idCourse;
    }

    @ModelAttribute("comments")
    public List<Comment> comments(@PathVariable("idCourse") Long idCourse) {
        ResponseEntity<Comment[]> commentsResponseEntity = commentService.getAllCommentsInAContent(idCourse);
        return Arrays.asList(commentsResponseEntity.getBody());
    }

    @ModelAttribute("commentToAdd")
    public ContentWrapper commentToAdd() {
        return new ContentWrapper();
    }

    @ModelAttribute("courseAnnouncements")
    public List<Announcment> courseAnnouncments(@PathVariable("idCourse") Long idCourse) {
        ResponseEntity<Announcment[]> courseAnnouncementsResponse = courseAnnouncmentService.getCourseAnnouncmentsOfACourse(idCourse);
        return Arrays.asList(courseAnnouncementsResponse.getBody());
    }

    @GetMapping("/{idCourse}")
    public String courseDetails(Model model, @PathVariable("idCourse") Long idCourse) {
        this.addToModelIfCommentsAreAlreadyLiked(model, idCourse);
        return "course/course-details";
    }

    @PostMapping("/{idCourse}")
    public String submitNewComment(Model model, @PathVariable("idCourse") Long idCourse, @ModelAttribute("commentToAdd") @Valid ContentWrapper commentToAdd, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            this.addToModelIfCommentsAreAlreadyLiked(model, idCourse);
            return "course/course-details";
        }

        this.commentService.addCommentInAContent(idCourse, commentToAdd);

        return "redirect:/course/details/" + idCourse;
    }

    @PostMapping("/{idCourse}/comment/{idComment}/unlike")
    public String unlikeComment(@PathVariable("idCourse") Long courseId, @PathVariable("idComment") Long commentId) {
        return this.toggleLikeAComment(courseId, commentId);
    }

    @PostMapping("/{idCourse}/comment/{idComment}/like")
    public String likeComment(@PathVariable("idCourse") Long courseId, @PathVariable("idComment") Long commentId) {
        return this.toggleLikeAComment(courseId, commentId);
    }

    @PostMapping("/{idCourse}/sendFriendRequest/{idRequestedTo}")
    public String sendFriendRequest(@PathVariable("idCourse") Long idCourse, @PathVariable("idRequestedTo") Long idRequestedTo) {
        this.friendshipService.sendFriendRequest(idRequestedTo);
        return "redirect:/course/details/" + idCourse;
    }

    private void addToModelIfCommentsAreAlreadyLiked(Model model, Long idCourse) {
        List<Comment> comments = comments(idCourse);
        List<User> students = students(idCourse);
        Map<String, Boolean> areLikedComments = new HashMap<>();
        Map<String, Integer> numberOfLikesInComments = new HashMap<>();
        Map<String, Boolean> isStudentAFriend = new HashMap<>();
        comments.forEach(comment -> areLikedComments.put(comment.getId().toString() , this.commentService.findIfACommentIsAlreadyLiked(comment.getId()).getBody()));
        comments.forEach(comment -> numberOfLikesInComments.put(comment.getId().toString(), this.likeService.getLikesOfAComment(comment.getId()).getBody().length));
        students.forEach(student -> {
            Friendship friendship = this.friendshipService.findIfFriendshipExists(student.getId()).getBody();
            Boolean valueToPutInModel = false;
            if(friendship != null) {
                if(friendship.getActive()) {
                    valueToPutInModel = true;
                } else {
                    valueToPutInModel = null;
                }
            }
            isStudentAFriend.put(student.getId().toString(), valueToPutInModel );
        });

        model.addAttribute("areLikedComments", areLikedComments);
        model.addAttribute("numberOfLikesInComments", numberOfLikesInComments);
        model.addAttribute("isStudentAFriend", isStudentAFriend);
    }

    private String toggleLikeAComment(Long courseId, Long commentId) {
        this.likeService.toggleLikeAComment(commentId);
        return "redirect:/course/details/" + courseId;
    }


}
