// tag::runner[]
package minitwitter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by balajiganapathise on 27/8/15.
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
// end::runner[]

@RestController
@RequestMapping("/api")
class MiniTwitterAPIController {
    @RequestMapping(value="/users/{userId}/profile", method = RequestMethod.GET)
    String getUserProfile(@PathVariable String userId) {
        return "User profile for User " + userId;
    }
}