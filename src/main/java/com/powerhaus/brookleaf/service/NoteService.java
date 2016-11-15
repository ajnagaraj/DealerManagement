package com.powerhaus.brookleaf.service;

import com.powerhaus.brookleaf.entity.Note;

import java.util.List;

public interface NoteService {
    
    List<Note> findNotes(Long dealerId);
    
    Note updateNote(Note note);
    
    Note createNote(Note note);
}
