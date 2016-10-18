package com.powerhaus.brookleaf.controller;

import com.powerhaus.brookleaf.forms.Credentials;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dealers")
public class DealersController {
    private static final String DEALERS_VIEW = "all-dealers";
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String showDealers(Model model) {
        
        return DEALERS_VIEW;
    }
}
