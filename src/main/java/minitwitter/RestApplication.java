// tag::runner[]
package minitwitter;
import minitwitter.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Created by balajiganapathise on 27/8/15.
 */
@SpringBootApplication
public class RestApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
    @Autowired
    UserDao userRepository;

////    @Override
////    public void run(String... strings) throws Exception {
//////        userRepository.save(new TUser("Balaji", "balaji@balaji.com", "balaji"));
//////        userRepository.save(new TUser("Deepa", "deepa@deepa.com", "deepa"));
//////        userRepository.save(new TUser("xyz", "xyz@xyz.com", "xyz"));
////    }
//    @Bean(name = "viewResolver")
//    public InternalResourceViewResolver getViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/resources/templates/");
//        viewResolver.setSuffix(".html");
//        return viewResolver;
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("resources/**")
////                .addResourceLocations("/resources/");
////        registry.addResourceHandler("/static/**")
////                .addResourceLocations("/static/**");
//    }
}
// end::runner[]

