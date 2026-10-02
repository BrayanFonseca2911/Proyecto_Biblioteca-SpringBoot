package com.example.biblioteca.dto;

import java.util.List;

public record AuthorResponse(Long id,
                             String nombre,
                             List<BookResponse> libros) {


}
