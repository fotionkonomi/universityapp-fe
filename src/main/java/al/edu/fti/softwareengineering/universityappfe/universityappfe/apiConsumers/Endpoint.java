package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Endpoint {

    @Value("${remote.ws.rootUri}")
    private String remoteRootUri;

    @Value("${endpoint.authentication}")
    private String authentication;

    @Value("${endpoint.find.user.email}")
    private String findUserByEmail;

    @Value("${endpoint.user.register}")
    private String signup;
}
