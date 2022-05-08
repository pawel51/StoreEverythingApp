package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.dto.NoteDto;
import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.repository.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> findAll(){
        log.info("Getting all notes");
        return noteRepository.findAll();
    }

    public Note getNote(Long id){
        log.info("Getting note with id = " + id);
        return noteRepository.getById(id);
    }

    public Note addNote(Note note){
        noteRepository.save(note);
        log.info("Added note with id = "+note.getId());
        return note;
    }

    public Note update(Long id, NoteDto noteDto) {
        Note note = getNote(id);

        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setCategory(noteDto.getCategory());
        note.setReminder(noteDto.getReminder());

        note = noteRepository.save(note);
        log.info("Note with id " + id + " has been updated.");
        return note;
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
        log.info("Group with id " + id + " has been removed.");
    }
}