package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.CommentService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.LikeService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.PostService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.ContentWrapper;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Post;
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
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @ModelAttribute("post")
    public Post post(@PathVariable("idPost") Long idPost) {
        return this.postService.findById(idPost).getBody();
    }

    @ModelAttribute("idPost")
    public Long idPost(@PathVariable("idPost") Long idPost) {
        return idPost;
    }


    @ModelAttribute("comments")
    public List<Comment> comments(@PathVariable("idPost") Long idPost) {
        ResponseEntity<Comment[]> commentsResponseEntity = commentService.getAllCommentsInAContent(idPost);
        return Arrays.asList(commentsResponseEntity.getBody());
    }

    @ModelAttribute("likes")
    public List<Like> likesOfThePost(@PathVariable("idPost") Long idPost) {
        ResponseEntity<Like[]> likesResponseEntity = likeService.getLikesOfAContent(idPost);
        return Arrays.asList(likesResponseEntity.getBody());
    }

    @ModelAttribute("commentToAdd")
    public ContentWrapper commentToAdd() {
        return new ContentWrapper();
    }

    @GetMapping("/{idPost}")
    public String postDetails(Model model, @PathVariable("idPost") Long idPost) {
        this.addToModelIfCommentsAreAlreadyLiked(model, idPost);
        return "post/post-details";
    }

    @PostMapping("/{idPost}")
    public String submitNewComment(Model model, @PathVariable("idPost") Long idPost, @ModelAttribute("commentToAdd") @Valid ContentWrapper commentToAdd, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            this.addToModelIfCommentsAreAlreadyLiked(model, idPost);
            return "post/post-details";
        }

        this.commentService.addCommentInAContent(idPost, commentToAdd);

        return "redirect:/post/" + idPost;
    }

    @PostMapping("/{idPost}/comment/{idComment}/unlike")
    public String unlikeComment(@PathVariable("idPost") Long idPost, @PathVariable("idComment") Long commentId) {
        return this.toggleLikeAComment(idPost, commentId);
    }

    @PostMapping("/{idPost}/comment/{idComment}/like")
    public String likeComment(@PathVariable("idPost") Long idPost, @PathVariable("idComment") Long commentId) {
        return this.toggleLikeAComment(idPost, commentId);
    }

    @PostMapping("/{idPost}/unlike")
    public String unlikeContent(@PathVariable("idPost") Long idPost) {
        return this.toggleLikeAContent(idPost);
    }

    @PostMapping("/{idPost}/like")
    public String likeContent(@PathVariable("idPost") Long idPost) {
        return this.toggleLikeAContent(idPost);
    }


    private void addToModelIfCommentsAreAlreadyLiked(Model model, Long idPost) {
        List<Comment> comments = comments(idPost);
        Map<String, Boolean> areLikedComments = new HashMap<>();
        Map<String, Integer> numberOfLikesInComments = new HashMap<>();
        comments.forEach(comment -> areLikedComments.put(comment.getId().toString() , this.commentService.findIfACommentIsAlreadyLiked(comment.getId()).getBody()));
        comments.forEach(comment -> numberOfLikesInComments.put(comment.getId().toString(), this.likeService.getLikesOfAComment(comment.getId()).getBody().length));

        model.addAttribute("areLikedComments", areLikedComments);
        model.addAttribute("numberOfLikesInComments", numberOfLikesInComments);
        model.addAttribute("isContentAlreadyLiked", this.likeService.findIfContentIsAlreadyLiked(idPost).getBody());
    }

    private String toggleLikeAComment(Long idPost, Long commentId) {
        this.likeService.toggleLikeAComment(commentId);
        return "redirect:/post/" + idPost;
    }

    private String toggleLikeAContent(Long idPost) {
        this.likeService.toggleLikeAContent(idPost);
        return "redirect:/post/" + idPost;
    }

}
