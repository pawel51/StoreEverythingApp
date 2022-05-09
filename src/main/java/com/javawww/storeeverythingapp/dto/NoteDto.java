package com.javawww.storeeverythingapp.dto;

import com.javawww.storeeverythingapp.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NoteDto {
    private String title;
    private String content;
    private Category category;
    private LocalDateTime reminder;
}
