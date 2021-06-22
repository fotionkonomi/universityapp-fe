package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller.ajax;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ajax/friendship")
public class FriendshipRestController {

    @Autowired
    private FriendshipService friendshipService;

    @PutMapping("/acceptFriendRequest/{idRequestedBy}")
    public void acceptFriendRequest(@PathVariable("idRequestedBy") Long idRequestedBy) {
        this.friendshipService.acceptFriendRequest(idRequestedBy);
    }

    @DeleteMapping("/declineFriendRequest/{idRequestedBy}")
    public void declineFriendRequest(@PathVariable("idRequestedBy") Long idRequestedBy) {
        this.friendshipService.declineFriendRequest(idRequestedBy);
    }
}
