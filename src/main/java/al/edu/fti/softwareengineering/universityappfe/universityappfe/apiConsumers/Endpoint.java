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

    @Value("${endpoint.course.available}")
    private String availableCourses;

    @Value("${endpoint.course.enrolled}")
    private String enrolledCourses;

    @Value("${endpoint.course.enroll}")
    private String enrollInACourse;

    @Value("${endpoint.course.drop}")
    private String dropACourse;

    @Value("${endpoint.course.friendCourses}")
    private String coursesOfAFriend;

    @Value("${endpoint.user.studentsEnrolled.course}")
    private String studentsOfACourse;

    @Value("${endpoint.content.comment}")
    private String commentsOfAContent;

    @Value("${endpoint.like.comment}")
    private String likeAComment;

    @Value("${endpoint.like.content}")
    private String likeAContent;

    @Value("${endpoint.comment.alreadyLiked}")
    private String findIfACommentIsAlreadyLiked;

    @Value("${endpoint.content.alreadyLiked}")
    private String findIfAContentIsAlreadyLiked;

    @Value("${endpoint.friendship.exists}")
    private String doesFriendshipExist;

    @Value("${endpoint.friendship.friendRequest}")
    private String sendFriendRequest;

    @Value("${endpoint.notification.loggedUser}")
    private String notificationsOfCurrentUser;

    @Value("${endpoint.notification.see}")
    private String seeNotification;

    @Value("${endpoint.friendship.accept}")
    private String acceptFriendRequest;

    @Value("${endpoint.friendship.decline}")
    private String declineFriendRequest;

    @Value("${endpoint.friendship.friendRequests.loggedUser}")
    private String friendRequestsOfLoggedUser;

    @Value("${endpoint.post}")
    private String post;

    @Value("${endpoint.posts.showToLoggedUser}")
    private String postsToShowLoggedUser;

    @Value("${endpoint.course.announcements}")
    private String announcementsForACourse;

    @Value("${endpoint.user.friends}")
    private String friendsOfLoggedUser;

    @Value("${endpoint.user.profile}")
    private String loggedUserProfile;
}
