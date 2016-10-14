package com.powerhaus.brookleaf.controller;


import org.apache.commons.logging.Log;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class LoginControllerTest {
    
    @Test
    public void shouldShowLoginPageForDefaultUrl() throws Exception {
        LoginController controller = new LoginController();
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/"))
                .andExpect(view().name("login"));
    }
    
    
    @Test
    public void shouldShowLoginPageForPowerhausUrl() throws Exception {
        LoginController controller = new LoginController();
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/powerhaus"))
                .andExpect(view().name("login"));
    }
        
}
