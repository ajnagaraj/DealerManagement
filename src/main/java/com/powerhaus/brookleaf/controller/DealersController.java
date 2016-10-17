package com.powerhaus.brookleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dealers")
public class DealersController {
    
    @RequestMapping(method = RequestMethod.GET)
    public String showDealers() {
        return "all-dealers";
    }
}
