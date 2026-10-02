package com.example.biblioteca.service;

import com.example.biblioteca.dto.AuthorResponse;
import com.example.biblioteca.dto.BookResponse;
import com.example.biblioteca.dto.CreateAuthorRequest;
import com.example.biblioteca.dto.CreateBookRequest;


import java.util.List;

public interface AuthorService {
    /*
     * crear author
     * listar author
     * buscar autor por id
     * agregar un libro
     */
    AuthorResponse createAuthor(CreateAuthorRequest request);
    List<AuthorResponse> listAuthors();
    AuthorResponse buscarPorId(Long id);
    BookResponse agregarLibro(CreateBookRequest request);}

