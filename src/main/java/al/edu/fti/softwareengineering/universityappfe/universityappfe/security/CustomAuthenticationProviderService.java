package al.edu.fti.softwareengineering.universityappfe.universityappfe.security;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.Endpoint;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.User;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.authentication.AuthenticationRequest;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.authentication.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;

import java.util.ArrayList;

@Service
@Primary
public class CustomAuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    private RestOperations restOperations;

    @Autowired
    private Endpoint endpoint;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken authenticationToken = null;

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        AuthenticationRequest authRequest = new AuthenticationRequest(email, password);
        ResponseEntity<AuthenticationResponse> authResponse = restOperations.postForEntity(endpoint.getRemoteRootUri() + endpoint.getAuthentication(), new HttpEntity<>(authRequest), AuthenticationResponse.class);
        if (authResponse.getStatusCode() == HttpStatus.OK) {
            String jwt = authResponse.getBody().getJwtToken();
            if (StringUtils.hasText(jwt)) {
                HttpHeaders headers = new HttpHeaders();
                headers.set("authorization", "Bearer " + jwt);
                ResponseEntity<User> userResponse = restOperations.postForEntity(endpoint.getRemoteRootUri() + endpoint.getFindUserByEmail(), new HttpEntity<>(email, headers), User.class);
                User user = userResponse.getBody();
                user.setToken(jwt);
                authenticationToken = new UsernamePasswordAuthenticationToken(
                        user, password, new ArrayList<>());
            }
        } else {
            throw new UsernameNotFoundException("Email " + email + " not found");
        }

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
