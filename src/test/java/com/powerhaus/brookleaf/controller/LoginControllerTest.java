package com.powerhaus.brookleaf.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

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
        
    @Test
    public void shouldSuccessfullyLoginAndRedirectToDealers() throws Exception {
        LoginController controller = new LoginController();
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(post("/")
                .param("email", "anagaraj@gmail.com")
                .param("password", "anagaraj"))
                .andExpect(redirectedUrl("/dealers"));
    }

    @Test
    public void shouldValidateEmailAndPasswordForMaximumCharacters() throws Exception {
        LoginController controller = new LoginController();
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(post("/")
                .param("email", "emailHasMoreThanRequiredNumberOfCharactersAndIsOutOfRange")
                .param("password", "passwordHasMoreThanRequiredNumberOfCharactersAndIsOutOfRange"))
                .andExpect(model().attributeHasFieldErrors("credentials", "email", "password"))
                .andExpect(view().name("login"));
    }
    
    @Test
    public void shouldValidateEmailAndPasswordIfEmpty() throws Exception {
        LoginController controller = new LoginController();
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(post("/")
                .param("email", "")
                .param("password", ""))
                .andExpect(model().attributeHasFieldErrors("credentials", "email", "password"))
                .andExpect(view().name("login"));
    }
}
