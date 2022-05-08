package com.javawww.storeeverythingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private User owner;
    private String title;
    private String content;
    @ManyToOne
    private Category category;
    @Column(name = "created_at")
    private OffsetDateTime createdAt;
    private OffsetDateTime reminder;

    public Note(User owner, String title, String content, Category category, OffsetDateTime reminder) {
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = OffsetDateTime.now();
        this.reminder = reminder;
    }
}