package com.powerhaus.brookleaf.repository;

import com.powerhaus.brookleaf.config.DealerManagementRootConfiguration;
import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.entity.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DealerManagementRootConfiguration.class)
@ActiveProfiles("development")
public class NoteRepositoryTest {
    
    @Autowired
    private NoteRepository noteRepository;
    
    @Test
    public void shouldConnectToTheDatabase() {
        List<Note> notes = noteRepository.findByDealerId(1L);
        assertThat(notes).isNotNull();
    }
    
    @Test
    public void shouldFindNotesAssociatedWithDealer() {
        List<Note> notes = noteRepository.findByDealerId(1L);
        
        Dealer dealer = Dealer.builder()
                .withId(1L)
                .build();
        
        Note noteId1  = Note.builder()
                .withId(1L)
                .withTime("15:6, Fri Nov 04 2016")
                .withText("Follow up failed, dealer not interested")
                .withDealer(dealer)
                .build();
        
        Note noteId2  = Note.builder()
                .withId(2L)
                .withTime("15:8, Fri Nov 04 2016")
                .withText("Dealer changed mind. Spoke of a ghost. He is ready to invest")
                .withDealer(dealer)
                .build();
        
        assertThat(notes).containsOnly(noteId1, noteId2);
    }
    
    @Test
    public void shouldNotFindNotesAssociatedWithDealer() {
        List<Note> notes = noteRepository.findByDealerId(100L);
        
        assertThat(notes).isEmpty();
    }
}
