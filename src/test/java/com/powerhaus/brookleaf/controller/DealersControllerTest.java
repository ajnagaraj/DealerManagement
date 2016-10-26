package com.powerhaus.brookleaf.controller;

import com.powerhaus.brookleaf.service.DealerService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class DealersControllerTest {
    
    @Test
    public void shouldShowDealersPage() throws Exception {
        DealersController controller = new DealersController(mock(DealerService.class));
    
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/dealers"))
                .andExpect(view().name("all-dealers"))
                .andExpect(model().attributeExists("dealers"));
    }
    
    @Test
    public void shouldShowCreateDealersPage() throws Exception {
        DealersController controller = new DealersController(mock(DealerService.class));
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/dealers/new"))
                .andExpect(view().name("new-dealer"));
    }
    
    @Test
    public void shouldProcessCreateDealersPage() throws Exception {
        DealersController controller = new DealersController(mock(DealerService.class));
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "Torque");
        params.add("email", "torque@whoosh.com");
        params.add("phone", "2131231");
        params.add("associate", "Speedy John");
        params.add("address.street", "88 Dreadful Road");
        params.add("address.city", "Drearile");
        params.add("address.zone", "Pink");
        params.add("address.postcode", "PSN 9EPL");
        params.add("address.country", "Sugar City");
        
        mockMvc.perform(post("/dealers/new")
                .params(params))
                .andExpect(redirectedUrl("/dealers"));
    }
    
    @Test
    public void shouldValidatePhone() throws Exception {
        DealersController controller = new DealersController(mock(DealerService.class));
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        String invalidPhone = "ABC9012-SDBA";
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "Torque");
        params.add("email", "torque@speed.com");
        params.add("phone", invalidPhone);
        params.add("associate", "Speedy John");
        params.add("address.street", "88 Dreadful Road");
        params.add("address.city", "Drearile");
        params.add("address.zone", "Pink");
        params.add("address.postcode", "PSN 9EPL");
        params.add("address.country", "Sugar City");
        
        mockMvc.perform(post("/dealers/new")
                .params(params))
                .andExpect(model().attributeHasFieldErrors("dealer", "phone"))
                .andExpect(view().name("new-dealer"));
    }
}
