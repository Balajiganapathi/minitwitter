package minitwitter.rest;

import minitwitter.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    ResponseEntity<TUser> getUserProfile(@PathVariable Long userId) {
        TUser u = userRepository.findById(userId);
        if(u == null) {
            return new ResponseEntity<TUser>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<TUser>(u, HttpStatus.OK);
    }

    @RequestMapping(value="/users/profile", method = RequestMethod.GET)
    ResponseEntity<TUser> getCurrentUserProfile(@RequestParam(required = true) long sessionId){
        return new ResponseEntity<TUser>(getSessionUser(sessionId), HttpStatus.OK);
    }

    @RequestMapping(value="/users/{userId}/tweets", method = RequestMethod.GET)
    ResponseEntity<List<Tweet>> getUserTweets(@PathVariable Long userId) {
        return new ResponseEntity<List<Tweet>>(tweetRepo.findByUser(userRepository.findById(userId)), HttpStatus.OK);
    }

    @RequestMapping(value="/users/tweets", method = RequestMethod.GET)
    ResponseEntity<List<Tweet>> getCurrentUserTweet(@RequestParam(required = true) long sessionId){
        return new ResponseEntity<List<Tweet>>(tweetRepo.findByUser(getSessionUser(sessionId)), HttpStatus.OK);
    }

    @RequestMapping(value= "/tweets/{tweetId}", method = RequestMethod.GET)
    ResponseEntity<Tweet> getTweet(@PathVariable Long tweetId) {
        return new ResponseEntity<Tweet>(tweetRepo.findByTweetId(tweetId), HttpStatus.OK);
    }

    @RequestMapping(value= "/users/feed", method = RequestMethod.GET)
    ResponseEntity<List<Tweet>> getFeed(@RequestParam(required = true) long sessionId){
        TUser user = getSessionUser(sessionId);
        List<Followers> followees = followersRepo.findByFollower(user);
        List<Tweet> feed = new ArrayList<Tweet>();
        for(Followers f: followees) {
            List<Tweet> tweets_f = tweetRepo.findByUser(f.getFollowee());
            feed.addAll(tweets_f);
        }

        return new ResponseEntity<List<Tweet>>(feed, HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/followers", method = RequestMethod.GET)
    ResponseEntity<List<Followers>> getUserFollowers(@PathVariable Long userId) {
        return new ResponseEntity<List<Followers>>(followersRepo.findByFollowee(userRepository.findById(userId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{userId}/followee", method = RequestMethod.GET)
    ResponseEntity<List<Followers>> getUserFollowee(@PathVariable Long userId) {
        return new ResponseEntity<List<Followers>>(followersRepo.findByFollower(userRepository.findById(userId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/followers", method = RequestMethod.GET)
    ResponseEntity<List<Followers>> getCurrentFollowers(@RequestParam(required = true) long sessionId) throws Exception {
        return new ResponseEntity<List<Followers>>(followersRepo.findByFollowee(getSessionUser(sessionId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/followee", method = RequestMethod.GET)
    ResponseEntity<List<Followers>> getCurrentFollowee(@RequestParam(required = true) long sessionId) throws Exception {
        return new ResponseEntity<List<Followers>>(followersRepo.findByFollower(getSessionUser(sessionId)), HttpStatus.OK);
    }

    // POST requests
    @RequestMapping(value="/users/register", method=RequestMethod.POST)
    ResponseEntity<TUser> register(@RequestBody TUser user) {
        TUser newUser = userRepository.save(user);
        return new ResponseEntity<TUser>(newUser, HttpStatus.OK);
    }

    @RequestMapping(value="/users/login", method=RequestMethod.POST)
    ResponseEntity<Session> login(@RequestParam(required=true) Map<String, String> params) throws Exception {
        String username = params.get("username");
        String password = params.get("password");
        TUser user = userRepository.findByName(username);

        return new ResponseEntity<Session>(sessionRepo.save(new Session(user)), HttpStatus.OK);
    }

    @RequestMapping(value="/users/logout", method=RequestMethod.POST)
    ResponseEntity<String> logout(@RequestParam(required = true) long sessionId) throws Exception {
        TUser user = getSessionUser(sessionId);
        sessionRepo.delete(sessionId);
        return new ResponseEntity<String>(user.getName() + " logged out succesfully", HttpStatus.OK);
    }

    @RequestMapping(value="/users/tweets", method=RequestMethod.POST)
    ResponseEntity<Tweet> addTweet(@RequestParam(required = true) long sessionId, @RequestBody Map<String, String> body) {
        TUser user = getSessionUser(sessionId);
        Tweet tweet = new Tweet(user, body.getOrDefault("contents", ""));
        tweetRepo.save(tweet);
        return new ResponseEntity<Tweet>(tweet, HttpStatus.OK);
    }

    @RequestMapping(value="/users/follow/{followeeId}", method=RequestMethod.POST)
    ResponseEntity<Followers> follow(@PathVariable Long followeeId, @RequestParam(required = true) long sessionId) throws Exception{
        TUser follower = getSessionUser(sessionId);
        Followers followers = new Followers(follower, userRepository.findById(followeeId));
        return new ResponseEntity<Followers>(followersRepo.save(followers), HttpStatus.OK);
    }

    // PUT
    @RequestMapping(value="/users/profile", method=RequestMethod.PUT)
    ResponseEntity<TUser> modifyProfile(@RequestParam(required = true) long sessionId, @RequestBody Map<String, String> body) {
        TUser user = getSessionUser(sessionId);
        user.setEmail(body.getOrDefault("email", user.getEmail()));
        user.setName(body.getOrDefault("name", user.getName()));
        user.setPassword(body.getOrDefault("password", user.getPassword()));
        return new ResponseEntity<TUser>(user, HttpStatus.OK);
    }

    // DELETE
    @RequestMapping(value="/users/follow/{followeeId}", method=RequestMethod.DELETE)
    ResponseEntity<String> unfollow(@PathVariable Long followeeId, @RequestParam(required = true) long sessionId) throws Exception {
        TUser follower = getSessionUser(sessionId);

        List<Followers> tobeDead = followersRepo.findByFollowerAndFollowee(follower, userRepository.findById(followeeId));
        for(Followers x : tobeDead) {
            followersRepo.delete(x);
        }
        return new ResponseEntity<String>("Unfollowed " + userRepository.findById(followeeId).getName(), HttpStatus.OK);
    }
}
