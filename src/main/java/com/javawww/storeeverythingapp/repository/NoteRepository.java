package com.javawww.storeeverythingapp.repository;

import com.javawww.storeeverythingapp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
