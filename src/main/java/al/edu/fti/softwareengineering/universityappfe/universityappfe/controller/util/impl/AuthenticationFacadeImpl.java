package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller.util.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.controller.util.AuthenticationFacade;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {
    @Override
    public User getAuthenticatedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public String getToken() {
        return getAuthenticatedUser().getToken();
    }
}
