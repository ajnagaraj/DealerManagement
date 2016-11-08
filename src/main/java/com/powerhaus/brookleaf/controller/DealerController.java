package com.powerhaus.brookleaf.controller;

import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.error.DealerError;
import com.powerhaus.brookleaf.error.Error;
import com.powerhaus.brookleaf.exception.DealerValidationException;
import com.powerhaus.brookleaf.exception.ServiceException;
import com.powerhaus.brookleaf.form.DealerForm;
import com.powerhaus.brookleaf.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
@RequestMapping("/dealers")
public class DealerController {
    private static final String DEALERS_VIEW = "all-dealers";
    private static final String DEALERS_ATTRIBUTE = "dealers";
    
    private static final DealerError EMPTY_DEALER_ERROR = new DealerError();
    
    private DealerService dealerService;
    private ConversionService conversionService;
    
    @Autowired
    public DealerController(ConversionService conversionService, DealerService dealerService) {
        this.conversionService = conversionService;
        this.dealerService = dealerService;
    }
    
    @ModelAttribute
    public void addAttributeToModel(Model model) {
        model.addAttribute("dealer", new DealerForm());
    }
    
    @RequestMapping(method = GET)
    public String getDealers(Model model) {
        Set<Dealer> dealers = dealerService.findAllDealers();
        
        Set<DealerForm> dealerForms = dealers.stream()
                .map(dealer -> conversionService.convert(dealer, DealerForm.class))
                .collect(Collectors.toSet());
        
        model.addAttribute(DEALERS_ATTRIBUTE, dealerForms);
        
        return DEALERS_VIEW;
    }
    
    @RequestMapping(value = "/{id}", method = GET, produces = "application/json")
    @ResponseBody
    public Dealer getDealer(@PathVariable Long id) {
        return dealerService.findDealer(id);
    }
    
    @RequestMapping(method = POST, consumes = "application/json")
    @ResponseBody
    public Dealer createDealer(
            @Valid @RequestBody Dealer dealer,
            Errors errors) {
        
        if (errors.hasErrors()) {
            throw new DealerValidationException(errors);
        }
        
        return dealerService.createDealer(dealer);
    }
    
    @RequestMapping(method = PUT, consumes = "application/json")
    @ResponseBody
    public Dealer editDealer(@Valid @RequestBody Dealer dealer, Errors errors) {
        
        if (errors.hasErrors()) {
            throw new DealerValidationException(errors);
        }
        
        return dealerService.updateDealer(dealer);
    }
    
    @ExceptionHandler(DealerValidationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public DealerError invalidDealer(DealerValidationException validationError) {
        if (!validationError.getErrors().isPresent()) {
            return EMPTY_DEALER_ERROR;
        }
        
        DealerError error = conversionService.convert(validationError.getErrors().get(), DealerError.class);
        
        return error;
    }
    
    
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    public Error serviceFailure(ServiceException serviceError) {
        return new Error("We have just encountered a problem. We will get back to you shortly.");
    }
    
}
