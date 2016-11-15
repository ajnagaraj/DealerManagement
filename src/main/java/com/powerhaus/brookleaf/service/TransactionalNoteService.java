package com.powerhaus.brookleaf.service;

import com.powerhaus.brookleaf.entity.Note;
import com.powerhaus.brookleaf.exception.ServiceException;
import com.powerhaus.brookleaf.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static org.apache.commons.lang3.Validate.notNull;

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
        notNull(dealerId, "Dealer id cannot be null");
        
        try {
            List<Note> notes = noteRepository.findByDealerId(dealerId);
            
            return notes.stream()
                    .map(note -> Note.builder(note).build())
                    .collect(Collectors.toList());
            
        } catch (DataAccessException accessError) {
            throw new ServiceException("Failed to find notes", accessError);
        }
    }
    
    @Override
    public Note updateNote(Note note) {
        notNull(note, "Note cannot be null");
        
        if (note.getId() == null) {
            throw new ServiceException(format("Note: %s is not updatable", note));
        }
        
        try {
            Note updatedNote = noteRepository.save(note);
            
            return Note.builder(updatedNote).build();
        } catch (DataAccessException accessError) {
            throw new ServiceException("Failed to update note: " + note.getId(), accessError);
        }
    }
    
    @Override
    public Note createNote(Note note) {
        notNull(note, "Note cannot be null");
        
        try {
            Note savedNote = noteRepository.save(note);
            
            return Note.builder(savedNote).build();
        } catch (DataAccessException accessError) {
            throw new ServiceException(format("Failed to create note: %s", note), accessError);
        }
    }
}
