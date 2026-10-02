package com.example.biblioteca.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/books")
@Tag(name = "Libros", description = "registro de libros de un autor")
public class BookController {

}
