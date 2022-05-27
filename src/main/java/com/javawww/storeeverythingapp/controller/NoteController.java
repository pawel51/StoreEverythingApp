package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.dto.NoteDto;
import com.javawww.storeeverythingapp.model.Category;
import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.repository.UserRepository;
import com.javawww.storeeverythingapp.service.CategoryService;
import com.javawww.storeeverythingapp.service.NoteService;
import com.javawww.storeeverythingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final CategoryService categoryService;
    private final UserService userService;

    @GetMapping()
    public String getAll(Model model, @RequestParam(name = "titleSort", required = false) String titleSort){
        List<Note> noteList = noteService.findAll();
        if(titleSort == null){
            model.addAttribute("noteList", noteList);
        } else {
            switch (titleSort.toLowerCase()){
                case "ascending": model.addAttribute("noteList", noteList.stream().sorted(Comparator.comparing(Note::getTitle)).collect(Collectors.toList()));
                    break;
                case "descending": model.addAttribute("noteList", noteList.stream().sorted(Comparator.comparing(Note::getTitle).reversed()).collect(Collectors.toList()));
                    break;
                default: model.addAttribute("noteList", noteList);
                    break;
            }

        }
        return "note/getAll";
    }

    @GetMapping("/{id}")
    public String getNote(Model model, @PathVariable Long id) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note/getNote";
    }


//    TODO:
    @GetMapping("/add")
    public String createNote(Model model) {
        if (!model.containsAttribute("note")) {
            model.addAttribute("note", new Note());
        }
        if (!model.containsAttribute("categories")) {
            model.addAttribute("categories", categoryService.findAll());
        }
        if (!model.containsAttribute("categoryString")) {
            model.addAttribute("categoryString", "");
        }

        return "note/addNote";
    }

    @PostMapping("/add")
    public String addNote(Model model,
                           @Valid Note note,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            if (!model.containsAttribute("categories")) {
                model.addAttribute("categories", categoryService.findAll());
            }
            return "/note/addNote";
        }
        note.setCreatedAt(OffsetDateTime.now());

        UserModel u = userService.getUserByUsername("Jantex");
        note.setOwner(u);
        noteService.add(note);

        redirectAttributes.addFlashAttribute("success", "Record has been successfully added.");
        return "redirect:/note/" + note.getId();
    }

    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, Model model) {

        Note note = noteService.getById(id);
        NoteDto noteDto = new NoteDto(note.getTitle(), note.getContent(), note.getCategory(), note.getReminder());

        if (!model.containsAttribute("note")) {
            model.addAttribute("note", new NoteDto());
        }
        if (!model.containsAttribute("categories")) {
            model.addAttribute("categories", categoryService.findAll());
        }

        model.addAttribute("noteId", id);
        model.addAttribute("note", noteDto);
        return "note/edit";
    }

    @PutMapping("/{id}/update")
    public String updateNote(@PathVariable Long id,
                             @Valid @ModelAttribute("note") NoteDto noteDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.groupDto", bindingResult);
            redirectAttributes.addFlashAttribute("note", noteDto);
            return "redirect:/note/" + id;
        }

        Note note = noteService.update(id, noteDto);

        redirectAttributes.addFlashAttribute("success", "Record has been successfully updated.");
        return "redirect:/note/" + note.getId();
    }

    @DeleteMapping("/{id}")
    public String deleteNote(@PathVariable("id") Long id) {
        noteService.delete(id);
        return "redirect:/note";
    }
}
