package minitwitter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by deepap on 7/9/15.
 */
@Controller
public class MiniTwitterWebController {
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
