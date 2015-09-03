package minitwitter.web;

import minitwitter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @Autowired
    TweetRepo tweetRepo;

    private TUser getSessionUser(long sessionId) {
        return sessionRepo.findBySessionId(sessionId).getUser();
    }
    // GET requests
    @RequestMapping(value="/users/{userId}/profile", method = RequestMethod.GET)
    TUser getUserProfile(@PathVariable Long userId) {
        TUser u = userRepository.findById(userId);
        return u;
    }

    @RequestMapping(value="/users/profile", method = RequestMethod.GET)
    TUser getCurrentUserProfile(@RequestParam(required = true) long sessionId){
        return getSessionUser(sessionId);
    }

    @RequestMapping(value="/users/{userId}/tweets", method = RequestMethod.GET)
    List<Tweet> getUserTweets(@PathVariable Long userId) {
        return tweetRepo.findByUser(userRepository.findById(userId));
    }

    @RequestMapping(value="/users/tweets", method = RequestMethod.GET)
    List<Tweet> getCurrentUserTweet(@RequestParam(required = true) long sessionId){
        return tweetRepo.findByUser(getSessionUser(sessionId));
    }

    @RequestMapping(value= "/tweets/{tweetId}", method = RequestMethod.GET)
    Tweet getTweet(@PathVariable Long tweetId) {
        return tweetRepo.findByTweetId(tweetId);
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
