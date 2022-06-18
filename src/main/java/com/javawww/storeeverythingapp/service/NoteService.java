package com.javawww.storeeverythingapp.service;

import com.javawww.storeeverythingapp.dto.NoteDto;
import com.javawww.storeeverythingapp.model.Category;
import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.repository.CategoryRepository;
import com.javawww.storeeverythingapp.repository.NoteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class NoteService extends GenericManagementService<Note, NoteRepository>{
    private final CategoryRepository categoryRepository;

    public NoteService(NoteRepository repository, CategoryRepository categoryRepository) {
        super(repository);
        this.categoryRepository = categoryRepository;
    }

    public List<Note> findPersonNotes(String username){
        return findAll().stream()
                .filter(note -> note.getOwner().getUsername().equals(username)).collect(Collectors.toList());
    }

    public Map<String, Long> getNotesAmountByCategories(){
        Map<String, Long> notesAmountByCategories = new HashMap<>();
        for (Category cat: categoryRepository.findAll()) {
            notesAmountByCategories.put(cat.getName(), repository.findAll().stream()
                    .filter(note -> note.getCategory().getName().equals(cat.getName()))
                    .count());
        }
        return notesAmountByCategories;
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
