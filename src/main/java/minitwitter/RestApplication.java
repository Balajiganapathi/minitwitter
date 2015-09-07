// tag::runner[]
package minitwitter;
import minitwitter.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by balajiganapathise on 27/8/15.
 */
@SpringBootApplication
public class RestApplication implements CommandLineRunner{
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
    @Autowired
    UserDao userRepository;

    @Override
    public void run(String... strings) throws Exception {
//        userRepository.save(new TUser("Balaji", "balaji@balaji.com", "balaji"));
//        userRepository.save(new TUser("Deepa", "deepa@deepa.com", "deepa"));
//        userRepository.save(new TUser("xyz", "xyz@xyz.com", "xyz"));
    }
}
// end::runner[]

