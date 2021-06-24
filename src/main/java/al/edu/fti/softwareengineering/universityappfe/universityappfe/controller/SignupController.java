package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.SignupService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class SignupController {

    @Autowired
    private SignupService signupService;

    @ModelAttribute("user")
    public User newUser() {
        return new User();
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("user") @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().forEach(error -> log.error(error.getArguments() + error.getDefaultMessage()));
            return "signup";
        }
        this.signupService.signup(user);

        return "redirect:/1";
    }


}
