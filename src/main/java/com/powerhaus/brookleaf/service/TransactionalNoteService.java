package com.powerhaus.brookleaf.service;

import com.powerhaus.brookleaf.entity.Note;
import com.powerhaus.brookleaf.exception.ServiceException;
import com.powerhaus.brookleaf.repository.NoteRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionalNoteService implements NoteService {
    
    private NoteRepository noteRepository;
    
    @Autowired
    public TransactionalNoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    
    @Override
    @Transactional
    public List<Note> findNotes(Long dealerId) {
        Validate.notNull(dealerId, "Dealer id cannot be null");
        
        try {
            
            return noteRepository.findByDealerId(dealerId);
        } catch(DataAccessException accessError) {
            throw new ServiceException("Failed to find notes", accessError);
        }
    }
}
