package com.javawww.storeeverythingapp.dto;

import com.javawww.storeeverythingapp.model.Category;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private String title;
    private String content;
    private Category category;
    private OffsetDateTime reminder;
}
