package minitwitter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @RequestMapping("/profile/{userId}")
    public String profile() {
        return "profile";
    }

    @RequestMapping("/logout")
    public String logout() {
        return "logout";
    }

    @RequestMapping("/tweets/{tweetId}")
    public String tweet() {
        return "tweet";
    }

    @RequestMapping("/tweet")
    public String addTweet() {
        return "addTweet";
    }

    @RequestMapping("register")
    public String register() {
        return "register";
    }

    @RequestMapping("")
    public String feed() {
        return "feed";
    }
}
