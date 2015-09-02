package minitwitter.web;

import minitwitter.model.Session;
import minitwitter.model.SessionRepo;
import minitwitter.model.TUser;
import minitwitter.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by balajiganapathise on 2/9/15.
 */
@RestController
@RequestMapping("/api")
public class MiniTwitterAPIController {

    @Autowired
    UserDao userRepository;

    @Autowired
    SessionRepo sessionRepo;

    private TUser getSessionUser(long sessionId) {
        return sessionRepo.findBySessionId(sessionId).getUser();
    }
    // GET requests
    @RequestMapping(value="/users/{userId}/profile", method = RequestMethod.GET)
    TUser getUserProfile(@PathVariable Long userId) {
        TUser u = userRepository.findById(userId);
        // TODO: add Null pointer check and return meaningful error message
        return u;
    }

    @RequestMapping(value="/users/{userId}/tweets", method = RequestMethod.GET)
    String getUserTweets(@PathVariable String userId) {
        return "Listing tweets for user " + userId;
    }

    @RequestMapping(value= "/tweets/{tweetId}", method = RequestMethod.GET)
    String getTweet(@PathVariable String tweetId) {
        return "tweet for tweetId " +  tweetId;
    }

    @RequestMapping(value= "/users/{userId}/feed", method = RequestMethod.GET)
    String getFeed(@PathVariable String userId) {
        return "Feed for user " +  userId;
    }

    // POST requests
    @RequestMapping(value="/users/register", method=RequestMethod.POST)
    TUser register(@RequestBody TUser user) {
        return userRepository.save(user);
    }

    @RequestMapping(value="/users/login", method=RequestMethod.POST)
    Session login(@RequestParam(required=true) Map<String, String> params) throws Exception {
        String username = params.get("username");
        String password = params.get("password");
        TUser user = userRepository.findByName(username);

        return sessionRepo.save(new Session(user));
    }

    @RequestMapping(value="/users/logout", method=RequestMethod.POST)
    String logout(@RequestParam(required = true) long sessionId) throws Exception {
        TUser user = getSessionUser(sessionId);
        sessionRepo.delete(sessionId);
        return user.getName() + " logged out succesfully";
    }

    @RequestMapping(value="/users/{userId}/tweets", method=RequestMethod.POST)
    String addTweet(@PathVariable String userId, @RequestBody String body) {
        return "Adding a tweet for user " + userId + " with data: " + body;
    }

    @RequestMapping(value="/users/{userId}/follow/{followeeId}", method=RequestMethod.POST)
    String follow(@PathVariable String userId, @PathVariable String followeeId) {
        return "User " + userId + " following User " + followeeId;
    }

    // PUT
    @RequestMapping(value="/users/{userId}/tweets", method=RequestMethod.PUT)
    String modifyProfile(@PathVariable String userId, @RequestBody String body) {
        return "Modifying profile of user " + userId + " with data: " + body;
    }

    // DELETE
    @RequestMapping(value="/users/{userId}/follow/{followeeId}", method=RequestMethod.DELETE)
    String unfollow(@PathVariable String userId, @PathVariable String followeeId) {
        return "User " + userId + " unfollowing User " + followeeId;
    }

}
