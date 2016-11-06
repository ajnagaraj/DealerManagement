package com.powerhaus.brookleaf.controller;

import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.entity.Note;
import com.powerhaus.brookleaf.service.NoteService;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Arrays.*;
import static java.util.Collections.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class NoteControllerTest {
    
    @Test
    public void shouldGetNotesAssociatedWithDealer() throws Exception {
        Note expectedNote = Note.builder()
                .withDealer(Dealer.builder().withId(1L).build())
                .withId(1L)
                .withText("Quirky dealer")
                .withTime("10:08, 8 Wed Nov 16")
                .build();
        
        NoteService noteService = mock(NoteService.class);
        when(noteService.findNotes(1L)).thenReturn(asList(expectedNote));
    
        NoteController noteController = new NoteController(noteService);
        MockMvc mockMvc = standaloneSetup(noteController).build();
    
        mockMvc.perform(get("/notes/dealer/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].text", is("Quirky dealer")));
    }
    
}
