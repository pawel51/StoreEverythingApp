package com.javawww.storeeverythingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private UserModel owner;
    private String title;
    private String content;
    @ManyToOne
    private Category category;
    @Column(name = "created_at")

    private OffsetDateTime createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime reminder;

    public Note(UserModel owner, String title, String content, Category category, LocalDateTime reminder) {
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = OffsetDateTime.now();
        this.reminder = reminder;
    }
}
