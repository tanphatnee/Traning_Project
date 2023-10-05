package d9.traning_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
    @RequestMapping(value = { "/", "/login" })
    public String login() {
        return "login";
    }

    @RequestMapping("/home")
    public String user() {
        return "home";
    }

}
