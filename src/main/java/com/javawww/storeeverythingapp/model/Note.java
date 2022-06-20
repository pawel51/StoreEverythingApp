package com.javawww.storeeverythingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotNull
    @ManyToOne
    private UserModel owner;

    @NotNull
    @Size(min=3, max=20, message = "{err.string.noteTitle}")
    private String title;

    @NotNull
    @Size(min=3, max=500, message = "{err.string.noteContent}")
    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Future(message = "{err.string.reminder}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime reminder;

    private String link;

    public Note(UserModel owner, String title, String content, Category category, LocalDateTime reminder) {
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = OffsetDateTime.now();
        this.reminder = reminder;
        this.link = generateNoteLink();

    }

    public Note(UserModel owner, String title, String content, Category category, OffsetDateTime createdAt, LocalDateTime reminder) {
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.reminder = reminder;
        this.link = generateNoteLink();
    }

    public Note() {
        this.link = generateNoteLink();
    }

    public Note(Long id, UserModel owner, String title, String content, Category category, OffsetDateTime createdAt, LocalDateTime reminder) {
        this.id = id;
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = createdAt;
        this.reminder = reminder;
        this.link = generateNoteLink();
    }

    private String generateNoteLink(){
        StringBuilder link = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            link.append((char) ((int)(Math.random()*1000)%26+97));
        }
        return link.toString();
    }
}
