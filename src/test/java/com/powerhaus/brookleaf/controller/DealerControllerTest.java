package com.powerhaus.brookleaf.controller;

import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.service.DealerService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class DealerControllerTest {
    
    private DealerController controller = new DealerController(
            mock(ConversionService.class), mock(DealerService.class));
    
    @Test
    public void shouldGetAllDealers() throws Exception {
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/dealers"))
                .andExpect(view().name("all-dealers"))
                .andExpect(model().attributeExists("dealers"));
    }
    
    @Test
    public void shouldFindDealerById() throws Exception {
        Dealer dealer = Dealer.builder()
                .withId(1L)
                .build();
        
        DealerService dealerService = mock(DealerService.class);
        when(dealerService.findDealer(1L)).thenReturn(dealer);
        
        DealerController controller = new DealerController(mock(ConversionService.class),
                dealerService);
        
        MockMvc mockMvc = standaloneSetup(controller).build();
        
        mockMvc.perform(get("/dealers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }
}
