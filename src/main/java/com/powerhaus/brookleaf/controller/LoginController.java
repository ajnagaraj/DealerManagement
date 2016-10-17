package com.powerhaus.brookleaf.controller;


import com.powerhaus.brookleaf.forms.Credentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping({ "/", "/powerhaus" })
public class LoginController  {
    private static final String LOGIN_VIEW = "login";
    private static final String REDIRECT_DEALERS = "redirect:/dealers";
    
    private static final String CREDENTIALS = "credentials";
    
    @ModelAttribute
    public void addAttributeToModel(Model model) {
        model.addAttribute(CREDENTIALS, new Credentials());
    }
    
    @RequestMapping(method=GET)
    public String showLoginPage() {
        
        return LOGIN_VIEW;
    }
    
    @RequestMapping(method = POST)
    public String processLoginPage(
            @Valid Credentials credentials,
            Errors errors) {
        
        if(errors.hasErrors()) {
            return LOGIN_VIEW;
        }
        
        return REDIRECT_DEALERS;
    }
}
