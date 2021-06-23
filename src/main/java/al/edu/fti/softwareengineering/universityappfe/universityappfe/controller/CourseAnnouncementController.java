package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CommentService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CourseAnnouncmentService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.LikeService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.ContentWrapper;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Announcment;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions.Comment;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.userInteractions.Like;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/announcement")
@Slf4j
public class CourseAnnouncementController {

    @Autowired
    private CourseAnnouncmentService courseAnnouncmentService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @ModelAttribute("courseAnnouncement")
    public Announcment courseAnnouncement(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment) {
        return this.courseAnnouncmentService.findById(idCourseAnnouncment).getBody();
    }

    @ModelAttribute("idCourseAnnouncment")
    public Long idCourseAnnouncment(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment) {
        return idCourseAnnouncment;
    }


    @ModelAttribute("comments")
    public List<Comment> comments(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment) {
        ResponseEntity<Comment[]> commentsResponseEntity = commentService.getAllCommentsInAContent(idCourseAnnouncment);
        return Arrays.asList(commentsResponseEntity.getBody());
    }

    @ModelAttribute("likes")
    public List<Like> likesOfTheAnnouncement(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment) {
        ResponseEntity<Like[]> likesResponseEntity = likeService.getLikesOfAContent(idCourseAnnouncment);
        return Arrays.asList(likesResponseEntity.getBody());
    }

    @ModelAttribute("commentToAdd")
    public ContentWrapper commentToAdd() {
        return new ContentWrapper();
    }

    @GetMapping("/{idCourseAnnouncment}")
    public String announcementDetails(Model model, @PathVariable("idCourseAnnouncment") Long idCourseAnnouncment) {
        this.addToModelIfCommentsAreAlreadyLiked(model, idCourseAnnouncment);
        return "announcement/announcement-details";
    }

    @PostMapping("/{idCourseAnnouncment}")
    public String submitNewComment(Model model, @PathVariable("idCourseAnnouncment") Long idCourseAnnouncment, @ModelAttribute("commentToAdd") @Valid ContentWrapper commentToAdd, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            this.addToModelIfCommentsAreAlreadyLiked(model, idCourseAnnouncment);
            return "announcement/announcement-details";
        }

        this.commentService.addCommentInAContent(idCourseAnnouncment, commentToAdd);

        return "redirect:/announcement/" + idCourseAnnouncment;
    }

    @PostMapping("/{idCourseAnnouncment}/comment/{idComment}/unlike")
    public String unlikeComment(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment, @PathVariable("idComment") Long commentId) {
        return this.toggleLikeAComment(idCourseAnnouncment, commentId);
    }

    @PostMapping("/{idCourseAnnouncment}/comment/{idComment}/like")
    public String likeComment(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment, @PathVariable("idComment") Long commentId) {
        return this.toggleLikeAComment(idCourseAnnouncment, commentId);
    }

    @PostMapping("/{idCourseAnnouncment}/unlike")
    public String unlikeContent(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment) {
        return this.toggleLikeAContent(idCourseAnnouncment);
    }

    @PostMapping("/{idCourseAnnouncment}/like")
    public String likeContent(@PathVariable("idCourseAnnouncment") Long idCourseAnnouncment) {
        return this.toggleLikeAContent(idCourseAnnouncment);
    }


    private void addToModelIfCommentsAreAlreadyLiked(Model model, Long idCourseAnnouncment) {
        List<Comment> comments = comments(idCourseAnnouncment);
        Map<String, Boolean> areLikedComments = new HashMap<>();
        Map<String, Integer> numberOfLikesInComments = new HashMap<>();
        comments.forEach(comment -> areLikedComments.put(comment.getId().toString() , this.commentService.findIfACommentIsAlreadyLiked(comment.getId()).getBody()));
        comments.forEach(comment -> numberOfLikesInComments.put(comment.getId().toString(), this.likeService.getLikesOfAComment(comment.getId()).getBody().length));

        model.addAttribute("areLikedComments", areLikedComments);
        model.addAttribute("numberOfLikesInComments", numberOfLikesInComments);
        model.addAttribute("isContentAlreadyLiked", this.likeService.findIfContentIsAlreadyLiked(idCourseAnnouncment).getBody());
    }

    private String toggleLikeAComment(Long idCourseAnnouncment, Long commentId) {
        this.likeService.toggleLikeAComment(commentId);
        return "redirect:/announcement/" + idCourseAnnouncment;
    }

    private String toggleLikeAContent(Long idCourseAnnouncment) {
        this.likeService.toggleLikeAContent(idCourseAnnouncment);
        return "redirect:/announcement/" + idCourseAnnouncment;
    }
}
