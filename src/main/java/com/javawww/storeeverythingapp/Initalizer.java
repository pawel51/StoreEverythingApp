package com.javawww.storeeverythingapp;

import com.javawww.storeeverythingapp.enums.*;
import com.github.javafaker.Faker;
import com.javawww.storeeverythingapp.model.Category;
import com.javawww.storeeverythingapp.model.Note;
import com.javawww.storeeverythingapp.model.UserModel;
import com.javawww.storeeverythingapp.repository.CategoryRepository;
import com.javawww.storeeverythingapp.repository.NoteRepository;
import com.javawww.storeeverythingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Component
@AllArgsConstructor
public class Initalizer implements CommandLineRunner {
    private final Faker faker = new Faker();

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        UserModel userModel1 = addUser("Anna", "Jantar", "Jantex", "example123", Role.LIMITEDUSER);
        UserModel userModel2 = addUser("Jan", "Kowalski", "Kowal", "example321", Role.FULLUSER);

        Note note1 = addNote(userModel1, "Title1", "Example content1");
        Note note2 = addNote(userModel1, "Title2", "Example content2");
        Note note3 = addNote(userModel2, "Title3", "Example content3");
        Note note4 = addNote(userModel1, "Title4", "Example content4");

        Category cat1 = addCategory("Do zrobienia");
        Category cat2 = addCategory("Wizyty");
        Category cat3 = addCategory("Ogłoszenia");
        Category cat4 = addCategory("Notatki");

    }

    public UserModel addUser(String name, String surname, String username, String password, Role role){
        UserModel userModel = new UserModel(name, surname, username, password,  role);
        userRepository.save(userModel);
        return userModel;
    }

    public Note addNote(UserModel owner, String title, String content){
        Note note = new Note(owner, title, content, null, LocalDateTime.now().plusHours(1));
        noteRepository.save(note);
        return note;
    }

    public Category addCategory(String name){
        return categoryRepository.save(new Category(name));
    }
}
