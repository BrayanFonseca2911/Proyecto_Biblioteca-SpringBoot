package com.example.biblioteca.controller;
import com.example.biblioteca.dto.AuthorResponse;
import com.example.biblioteca.dto.CreateAuthorRequest;
import com.example.biblioteca.service.AuthorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Autores", description = "Registro y consulta de autores con sus libros")
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @PostMapping
    public ResponseEntity<AuthorResponse> createAuthor(@RequestBody CreateAuthorRequest request){
        AuthorResponse response = authorService.createAuthor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping
    public ResponseEntity<List<AuthorResponse>> listAuthors(){
        return ResponseEntity.ok(authorService.listAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> bucarPorId(@PathVariable Long id){
        return ResponseEntity.ok(authorService.buscarPorId(id));
    }

}
