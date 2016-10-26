package com.powerhaus.brookleaf.controller;

import com.powerhaus.brookleaf.entity.Address;
import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.form.AddressForm;
import com.powerhaus.brookleaf.form.Credentials;
import com.powerhaus.brookleaf.form.DealerForm;
import com.powerhaus.brookleaf.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dealers")
public class DealersController {
    private static final String DEALERS_VIEW = "all-dealers";
    private static final String DEALERS_ATTRIBUTE = "dealers";
    private static final String CREATE_DEALERS_VIEW = "new-dealer";
    
    private static final String REDIRECT_DEALERS = "redirect:/dealers";
    private static final String REDIRECT_CREATE_DEALERS = "redirect:/dealers/new";
    
    
    private DealerService dealerService;
    
    @Autowired
    public DealersController(DealerService dealerService) {
        this.dealerService = dealerService;
    }
    
    @ModelAttribute
    public void addAttributeToModel(Model model) {
        model.addAttribute("dealer", new DealerForm());
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String showDealers(Model model) {
        Set<Dealer> dealers = dealerService.findAllDealers();
        
        model.addAttribute(DEALERS_ATTRIBUTE, toDealerForms(dealers));
        
        return DEALERS_VIEW;
    }
    
    private Set<DealerForm> toDealerForms(Set<Dealer> dealers) {
        Set<DealerForm> dealerForms = dealers.stream()
                .map(dealer -> dealer.toDealerForm())
                .collect(Collectors.toSet());
        
        return dealerForms;
    }
    
    @RequestMapping(path = "/new", method = RequestMethod.GET)
    public String showCreateDealer() {
        
        return CREATE_DEALERS_VIEW;
    }
    
    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public String processCreateDealer(
            @ModelAttribute("dealer") @Valid DealerForm dealerForm,
            Errors errors,
            RedirectAttributes model) {
        
        if(errors.hasErrors()) {
            return CREATE_DEALERS_VIEW;
        }
    
        dealerService.createDealer(dealerForm.toDealer());
        
        return REDIRECT_DEALERS;
    }
            
}
