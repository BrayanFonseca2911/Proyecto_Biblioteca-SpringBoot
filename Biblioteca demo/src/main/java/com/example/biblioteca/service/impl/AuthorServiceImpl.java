package com.example.biblioteca.service.impl;

import com.example.biblioteca.dto.AuthorResponse;
import com.example.biblioteca.dto.BookResponse;
import com.example.biblioteca.dto.CreateAuthorRequest;
import com.example.biblioteca.dto.CreateBookRequest;
import com.example.biblioteca.entities.Author;
import com.example.biblioteca.entities.Book;
import com.example.biblioteca.repositories.AuthorRepository;
import com.example.biblioteca.repositories.BookRepository;
import com.example.biblioteca.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public AuthorResponse createAuthor(CreateAuthorRequest request) {
        Author author = new Author(request.nombre());
        Author guardado = authorRepository.save(author);
        return mapToResponse(guardado);
    }

    public List<AuthorResponse> listAuthors() {
        return authorRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public AuthorResponse buscarPorId(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Author no encontrado"+id));

        return mapToResponse(author);
    }

    public BookResponse agregarLibro(CreateBookRequest request){
        Author author = authorRepository.findById(request.authorId())
                .orElseThrow(() -> new RuntimeException("Autor no encontrado con el ID: " + id));

        Book book = new Book(request.title());
        author.addBook(book);
        Book guardado = bookRepository.save(book);
        return new BookResponse(guardado.getId(), guardado.getTitle());

    }

    // mapeo manual de Entidad a Record dto
    private AuthorResponse mapToResponse(Author author) {
        List<BookResponse> LibrosDTO = author.getBooks()
                .stream()
                .map(book -> new BookResponse(book.getId(),book.getTitle()))
                .toList();

        return new AuthorResponse(author.getId(), author.getName(), LibrosDTO);
    }
}
