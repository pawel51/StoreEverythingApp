package com.javawww.storeeverythingapp.controller;

import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/note")
public class NoteController {
    private final NoteService noteService;

    @GetMapping()
    public String getAll(Model model){
        List<Note> noteList = noteService.findAll();
        model.addAttribute("noteList", noteList);
        return "note/getAll";
    }

    @GetMapping("/{id}")
    public String getNote(Model model, @PathVariable Long id) {
        Note note = noteService.getNote(id);
        model.addAttribute("note", note);
        return "note/getNote";
    }


//    TODO:
//    @GetMapping("/add")
//    public String createNote(Model model) {
//        if (!model.containsAttribute("note")) {
//            model.addAttribute("note", new Note());
//        }
//
//        return "note/add";
//    }
//
//    @PostMapping("/add")
//    public String addNote(@ModelAttribute("note") Note note,
//                           BindingResult bindingResult,
//                           RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.groupDto", bindingResult);
//            redirectAttributes.addFlashAttribute("note", note);
//            return "redirect:/note/add";
//        }
//
//        noteService.addNote(note);
//
//        redirectAttributes.addFlashAttribute("success", "Record has been successfully added.");
//        return "redirect:/note/" + note.getId();
//    }

//    @GetMapping("/{id}/edit")
//    public String editNote(@PathVariable Long id, Model model) {
//
//        Note note = noteService.getNote(id);
//        NoteDto noteDto = new NoteDto(note.getTitle(), note.getContent(), note.getCategory(), note.getReminder());
//
//        if (!model.containsAttribute("noteDto")) {
//            model.addAttribute("noteDto", new NoteDto());
//        }
//
//        model.addAttribute("noteId", id);
//        model.addAttribute("noteDto", noteDto);
//        return "note/edit";
//    }
//
//    @PutMapping("/{id}/update")
//    public String updateNote(@PathVariable Long id,
//                             @ModelAttribute("noteDto") NoteDto noteDto,
//                              BindingResult bindingResult,
//                              RedirectAttributes redirectAttributes) {
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.groupDto", bindingResult);
//            redirectAttributes.addFlashAttribute("noteDto", noteDto);
//            return "redirect:/note/" + id;
//        }
//
//        Note note = noteService.update(id, noteDto);
//
//        redirectAttributes.addFlashAttribute("success", "Record has been successfully updated.");
//        return "redirect:/note/" + note.getId();
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteNote(@PathVariable Long id) {
//        noteService.delete(id);
//
//        return "redirect:/note";
//    }
}
