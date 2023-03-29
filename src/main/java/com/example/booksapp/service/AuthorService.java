package com.example.booksapp.service;

import com.example.booksapp.model.Author;
import com.example.booksapp.model.Country;
import com.example.booksapp.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(String name, String surname, Long countryId);

    Optional<Author> save(AuthorDto authorDto);

    Optional<Author> edit(Long id, String name, String surname, Long countryId);

    Optional<Author> edit(Long id, AuthorDto authorDto);

    void deleteById(Long id);
}
