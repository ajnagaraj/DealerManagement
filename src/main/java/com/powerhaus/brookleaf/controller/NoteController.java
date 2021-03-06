package com.powerhaus.brookleaf.controller;

import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.entity.Note;
import com.powerhaus.brookleaf.exception.ServiceException;
import com.powerhaus.brookleaf.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/notes")
public class NoteController {
    
    private NoteService noteService;
    
    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    
    @RequestMapping(value = "/dealer/{dealerId}", method = GET)
    public List<Note> getNotesByDealerId(@PathVariable Long dealerId) {
        return noteService.findNotes(dealerId);
    }
    
    @RequestMapping(method = PUT)
    public Note editNote(@Valid @RequestBody Note note) {
        return noteService.updateNote(note);
    }
    
    @RequestMapping(method = POST)
    public Note createNote(@Valid @RequestBody Note note) {
        return noteService.createNote(note);
    }
    
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public Error handleServiceError(ServiceException serviceError) {
        return new Error("Oops! There was a problem while trying to get notes. We will resume shortly.");
    }
    
    
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Error handleUnknownError(Exception unknown) {
        return new Error("Oops! We have hit a glitch. We will resume shortly.");
    }
}
