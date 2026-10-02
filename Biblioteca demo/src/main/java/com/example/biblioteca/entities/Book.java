package com.example.biblioteca.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "books")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO INCREMENT
    Long id;
    @Column(nullable = false) // NOT NULL
    @NonNull
    private String title;

    @ManyToOne(fetch = FetchType.LAZY) // solo trae autores cuando se necesitan
    @JoinColumn(name = "autor_id", nullable = false)// columna foreign key en DB
    private Author author;

}
