// tag::runner[]
package minitwitter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

/**
 * Created by balajiganapathise on 27/8/15.
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// end::runner[]

@RestController
@RequestMapping("/api")
class MiniTwitterAPIController {

    // GET requests
    @RequestMapping(value="/users/{userId}/profile", method = RequestMethod.GET)
    String getUserProfile(@PathVariable String userId) {
        return "User profile for User " + userId;
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
    String register(@RequestBody String body) {
        return "Registering user with data: " + body;
    }

    @RequestMapping(value="/users/login", method=RequestMethod.POST)
    String login(@RequestBody String body) {
        return "Logging in user with following data: " + body;
    }

    @RequestMapping(value="/users/logout", method=RequestMethod.POST)
    String logout(@RequestBody String body) {
        return "Logging out user with data: " + body;
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

