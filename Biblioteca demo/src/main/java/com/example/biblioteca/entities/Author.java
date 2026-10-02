package com.example.biblioteca.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name ="authors")

public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NonNull
    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Book> books = new ArrayList<>();

    public Book addBook (Book book) {
        books.add(book);
        return book;
    }

}
