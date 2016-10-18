package com.powerhaus.brookleaf.controller;


import com.powerhaus.brookleaf.forms.Credentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.util.StringTokenizer;


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
            Errors errors,
            RedirectAttributes model) {
        
        if(errors.hasErrors()) {
            return LOGIN_VIEW;
        }
    
        StringTokenizer usernames = new StringTokenizer(credentials.getEmail(), "@");
        // The first token is the username
        if(usernames.hasMoreTokens()) {
            model.addFlashAttribute("username", usernames.nextToken());
        }
        
        return REDIRECT_DEALERS;
    }
}
