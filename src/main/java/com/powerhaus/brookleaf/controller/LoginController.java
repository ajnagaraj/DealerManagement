package com.powerhaus.brookleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping({ "/", "/powerhaus" })
public class LoginController  {
    private static final String LOGIN_VIEW = "login";
    
    @RequestMapping(method=GET)
    public String login() {
        return LOGIN_VIEW;
    }
}
