package com.powerhaus.brookleaf.repository;

import com.powerhaus.brookleaf.entity.Dealer;
import com.powerhaus.brookleaf.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    
    List<Note> findByDealerId(Long dealerId);
}
