package com.javawww.storeeverythingapp;

import com.javawww.storeeverythingapp.enums.*;
import com.github.javafaker.Faker;
import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.model.User;
import com.javawww.storeeverythingapp.repository.NoteRepository;
import com.javawww.storeeverythingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@AllArgsConstructor
public class Initalizer implements CommandLineRunner {
    private final Faker faker = new Faker();

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Override
    public void run(String... args) throws Exception {
        User user1 = addUser("Anna", "Jantar", "Jantex", "example123", Role.LIMITEDUSER);
        User user2 = addUser("Jan", "Kowalski", "Kowal", "example321", Role.FULLUSER);

        Note note1 = addNote(user1, "Title1", "Example content1");
        Note note2 = addNote(user1, "Title2", "Example content2");
        Note note3 = addNote(user2, "Title3", "Example content3");
        Note note4 = addNote(user1, "Title4", "Example content4");

    }

    public User addUser(String name, String surname, String login, String password, Role role){
        User user = new User(name, surname, login, password, role);
        userRepository.save(user);
        return user;
    }

    public Note addNote(User owner, String title, String content){
        Note note = new Note(owner, title, content, null, OffsetDateTime.now().plusHours(1));
        noteRepository.save(note);
        return note;
    }
}
