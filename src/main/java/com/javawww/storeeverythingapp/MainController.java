package com.javawww.storeeverythingapp;

import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.service.NoteService;
import com.javawww.storeeverythingapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class MainController {
    private final NoteService noteService;

    @GetMapping("/welcome")
    public String getIndexPage(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Note> todayNotes = noteService.findPersonNotes(userDetails.getUsername()).stream()
                .filter(note -> note.getReminder().getDayOfMonth() == OffsetDateTime.now().getDayOfMonth())
                .filter(note -> note.getReminder().getMonthValue() == OffsetDateTime.now().getMonthValue())
                .filter(note -> note.getReminder().getYear() == OffsetDateTime.now().getYear())
                .collect(Collectors.toList());
        if(todayNotes.isEmpty()){
            return "index";
        }
        model.addAttribute("todayNotes", todayNotes);
        return "indexTodayNotes";
    }
}
