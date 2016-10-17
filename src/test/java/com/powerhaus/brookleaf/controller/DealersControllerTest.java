package com.powerhaus.brookleaf.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class DealersControllerTest {
    
    @Test
    public void shouldShowDealers() throws Exception {
        DealersController controller = new DealersController();
    
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/dealers"))
                .andExpect(view().name("all-dealers"));
    }
}
