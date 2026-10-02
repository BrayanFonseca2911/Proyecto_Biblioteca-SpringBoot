package com.example.biblioteca.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateBookRequest (@NotBlank @Size(max = 120) String title, @NotBlank @Positive Long authorId) {

}
