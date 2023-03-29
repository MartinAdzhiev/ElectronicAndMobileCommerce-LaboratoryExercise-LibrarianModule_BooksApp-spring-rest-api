package com.example.booksapp.service;

import com.example.booksapp.model.Author;
import com.example.booksapp.model.Book;
import com.example.booksapp.model.dto.BookDto;
import com.example.booksapp.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies);

    Optional<Book> edit(Long id, BookDto bookDto);

    Optional<Book> markAsTaken(Long id);

    void deleteById(Long id);

}
