package com.javawww.storeeverythingapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotNull(message = "{err.string.category}")
    @Size(min=3, max=20, message="{err.string.categoryName}")
    private String name;

    public Category (String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

}
