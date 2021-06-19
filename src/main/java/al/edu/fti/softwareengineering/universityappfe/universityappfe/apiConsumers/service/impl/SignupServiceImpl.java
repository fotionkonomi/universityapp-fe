package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.Endpoint;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.SignupService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    private RestOperations restOperations;

    @Autowired
    private Endpoint endpoint;

    @Override
    public void signup(User user) {
        restOperations.exchange(endpoint.getRemoteRootUri() + endpoint.getSignup(), HttpMethod.POST, new HttpEntity<>(user), Void.class);
    }
}
