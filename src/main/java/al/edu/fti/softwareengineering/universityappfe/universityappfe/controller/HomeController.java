package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.PostService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.ContentWrapper;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.commentableAndLikeable.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private PostService postService;

    @ModelAttribute("content")
    public ContentWrapper content() {
        return new ContentWrapper();
    }

    @GetMapping("/{pageNumber}")
    public String home(@PathVariable("pageNumber") int pageNumber, Model model) {
        this.setModelsForIndexPage(pageNumber, model);
        return "index";
    }


    @PostMapping("/{pageNumber}")
    public String addPost(Model model, @PathVariable("pageNumber") int pageNumber, @ModelAttribute("content") @Valid ContentWrapper content, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getDefaultMessage()));
            this.setModelsForIndexPage(pageNumber, model);
            return "index";
        }

        postService.addPost(content);
        return "redirect:/1";
    }

    private void setModelsForIndexPage(int pageNumber, Model model) {
        ResponseEntity<Post[]> posts = postService.postsToShowLoggedUser(pageNumber);
        List<Post> postsToShowToUser = Arrays.asList(posts.getBody());
        model.addAttribute("postsToShowLoggedUser", postsToShowToUser);
        if(pageNumber > 1) {
            model.addAttribute("previousPage", pageNumber - 1);
        }
        if(postsToShowToUser.size() == 10) {
            model.addAttribute("nextPage", pageNumber + 1);
        }

    }
}
