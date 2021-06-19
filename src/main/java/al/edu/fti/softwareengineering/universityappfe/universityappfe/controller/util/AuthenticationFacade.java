package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller.util;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;

public interface AuthenticationFacade {
    User getAuthenticatedUser();

    String getToken();
}
