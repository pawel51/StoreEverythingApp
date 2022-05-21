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
@AllArgsConstructor
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
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

    public Note(UserModel owner, String title, String content, Category category, LocalDateTime reminder) {
        this.owner = owner;
        this.title = title;
        this.content = content;
        this.category = category;
        this.createdAt = OffsetDateTime.now();
        this.reminder = reminder;
    }
}
