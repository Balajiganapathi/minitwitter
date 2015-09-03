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

    @Autowired
    FollowersRepo followersRepo;

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

    @RequestMapping(value = "/users/{userId}/followers", method = RequestMethod.GET)
    List<Followers> getUserFollowers(@PathVariable Long userId) {
        return followersRepo.findByFollowee(userRepository.findById(userId));
    }

    @RequestMapping(value = "/users/{userId}/followee", method = RequestMethod.GET)
    List<Followers> getUserFollowee(@PathVariable Long userId) {
        return followersRepo.findByFollower(userRepository.findById(userId));
    }

    @RequestMapping(value = "/users/followers", method = RequestMethod.GET)
    List<Followers> getCurrentFollowers(@RequestParam(required = true) long sessionId) throws Exception {
        return followersRepo.findByFollowee(getSessionUser(sessionId));
    }

    @RequestMapping(value = "/users/followee", method = RequestMethod.GET)
    List<Followers> getCurrentFollowee(@RequestParam(required = true) long sessionId) throws Exception {
        return followersRepo.findByFollower(getSessionUser(sessionId));
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

    @RequestMapping(value="/users/tweets", method=RequestMethod.POST)
    Tweet addTweet(@RequestParam(required = true) long sessionId, @RequestBody Map<String, String> body) {
        TUser user = getSessionUser(sessionId);
        Tweet tweet = new Tweet(user, body.getOrDefault("contents", ""));
        tweetRepo.save(tweet);
        return tweet;
    }

    @RequestMapping(value="/users/follow/{followeeId}", method=RequestMethod.POST)
    Followers follow(@PathVariable Long followeeId, @RequestParam(required = true) long sessionId) throws Exception{
        TUser follower = getSessionUser(sessionId);
        Followers followers = new Followers(follower, userRepository.findById(followeeId));
        return followersRepo.save(followers);
    }

    // PUT
    @RequestMapping(value="/users/profile", method=RequestMethod.PUT)
    TUser modifyProfile(@RequestParam(required = true) long sessionId, @RequestBody Map<String, String> body) {
        TUser user = getSessionUser(sessionId);
        user.setEmail(body.getOrDefault("email", user.getEmail()));
        user.setName(body.getOrDefault("name", user.getName()));
        user.setPassword(body.getOrDefault("password", user.getPassword()));
        return user;
    }

    // DELETE
    @RequestMapping(value="/users/follow/{followeeId}", method=RequestMethod.DELETE)
    String unfollow(@PathVariable Long followeeId, @RequestParam(required = true) long sessionId) throws Exception {
        TUser follower = getSessionUser(sessionId);

        List<Followers> tobeDead = followersRepo.findByFollowerAndFollowee(follower, userRepository.findById(followeeId));
        for(Followers x : tobeDead) {
            followersRepo.delete(x);
        }
        return "unfollowed" + userRepository.findById(followeeId).getName();
    }
}
