package com.javawww.storeeverythingapp.dto;

import com.javawww.storeeverythingapp.model.Category;
import com.javawww.storeeverythingapp.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class NoteDto {
    @Size(min=3, max=20, message = "{err.string.noteTitle}")
    private String title;
    @Size(min=3, max=500, message = "{err.string.noteContent}")
    private String content;
    private Category category;
    @Future(message = "{err.string.reminder}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime reminder;
}
