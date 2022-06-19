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
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;
    private final CategoryService categoryService;
    private final UserService userService;

    private final ModelMapper modelMapper;

//    @ModelAttribute("note")
//    public NoteDto note() {
//        return new NoteDto();
//    }

    @GetMapping()
    public String getAll(Model model,
                         @RequestParam(name = "titleSort", required = false) String titleSort,
                         @RequestParam(name = "dateSort", required = false) String dateSort,
                         @RequestParam(name = "categorySort", required = false) String categorySort){
        List<Note> noteList = noteService.findAll();
        if(titleSort != null){
            switch (titleSort.toLowerCase()){
                case "ascending": noteList = noteList.stream().sorted(Comparator.comparing(Note::getTitle)).collect(Collectors.toList());
                    break;
                case "descending": noteList = noteList.stream().sorted(Comparator.comparing(Note::getTitle).reversed()).collect(Collectors.toList());
                    break;
            }
        }

        if(dateSort != null){{
            switch (dateSort.toLowerCase()){
                case "ascending": noteList = noteList.stream().sorted(Comparator.comparing(Note::getCreatedAt)).collect(Collectors.toList());
                    break;
                case "descending": noteList = noteList.stream().sorted(Comparator.comparing(Note::getCreatedAt).reversed()).collect(Collectors.toList());
                    break;
            }
        }}

        if(categorySort != null){{
            Map<String, Long> amountMap = noteService.getNotesAmountByCategories();
            switch (categorySort.toLowerCase()){
                case "ascending":
                    noteList = noteList.stream().filter(note -> note.getCategory()!=null).sorted(Comparator.comparing(note -> amountMap.get(note.getCategory().getName()))).collect(Collectors.toList());
                    Collections.reverse(noteList);
                    break;
                case "descending":
                    noteList = noteList.stream().filter(note -> note.getCategory()!=null).sorted(Comparator.comparing(note -> amountMap.get(note.getCategory().getName()))).collect(Collectors.toList());
                    break;
            }
        }}

        model.addAttribute("noteList", noteList);

        return "note/getAll";
    }

    @GetMapping("/mine")
    public String getNoteForSpecificUser(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Note> noteList = noteService.findPersonNotes(userDetails.getUsername());
        model.addAttribute("noteList", noteList);
        return "note/getUserNotes";
    }

    @GetMapping("/{id}")
    public String getNote(Model model, @PathVariable Long id) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "note/getNote";
    }

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
        //TODO:
        note.setCreatedAt(OffsetDateTime.now());
        //TODO:
        UserModel u = userService.getUserByUsername("Jantex");
        note.setOwner(u);
        noteService.add(note);

        redirectAttributes.addFlashAttribute("success", "Record has been successfully added.");
        return "redirect:/note/" + note.getId();
    }

    @GetMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, Model model) {


        if (!model.containsAttribute("note")) {
            Note note = noteService.getById(id);
            NoteDto noteDto = modelMapper.map(note, NoteDto.class);
            model.addAttribute("note", noteDto);
        }
        if (!model.containsAttribute("categories")) {
            model.addAttribute("categories", categoryService.findAll());
        }
        if (!model.containsAttribute("noteId")) {
            model.addAttribute("noteId", id);
        }
        return "note/edit";
    }



    @PostMapping("/{id}/update")
    public String updateNote(@PathVariable Long id,
                             @Valid @ModelAttribute("note") final NoteDto noteDto,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.note", bindingResult);
            redirectAttributes.addFlashAttribute("noteId", id);
            redirectAttributes.addFlashAttribute("note", noteDto);
            return "redirect:/note/edit/" + id;
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

    @RequestMapping("/share/{id}")
    public String shareNote(@PathVariable Long id, UriComponentsBuilder ucb) {
        noteService.findNoteById(id);

        URI uri = ucb.path(String.format("/shared/{id}", id)).build().toUri();

        return uri.toString();
    }
}
