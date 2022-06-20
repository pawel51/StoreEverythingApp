package com.javawww.storeeverythingapp.repository;

import com.javawww.storeeverythingapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note getNoteByLink(String link);
}
