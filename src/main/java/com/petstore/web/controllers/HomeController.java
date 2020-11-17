package com.petstore.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // this is used similarly as the @Component annotation
public class HomeController {

    @GetMapping("/welcome") // @GetMapping used to map the url specified in the ().
    public @ResponseBody String welcomeMessage(){ // @ResponseBody used to return the response below

        return "pet application is running!! yeah!!";
    }

    @GetMapping("/page")
    public String displayWelcomepage(){
        return "welcome"; // returns the html file named 'welcome.html'
    }

}
