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
public class NoteService extends GenericManagementService<Note, NoteRepository>{

    public NoteService (NoteRepository repository) {
        super(repository);
    }


    public Note update(Long id, NoteDto noteDto) {
        Note note = getById(id);

        note.setTitle(noteDto.getTitle());
        note.setContent(noteDto.getContent());
        note.setCategory(noteDto.getCategory());
        note.setReminder(noteDto.getReminder());

        note = add(note);
        log.info("Note with id " + id + " has been updated.");
        return note;
    }


}
