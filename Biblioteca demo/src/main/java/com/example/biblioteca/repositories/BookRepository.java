package com.example.biblioteca.repositories;
import com.example.biblioteca.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository // Se encarga de crear el CRUD
public interface BookRepository extends JpaRepository<Book, Long> {
}