package com.example.booksapp.service.impl;

import com.example.booksapp.model.Author;
import com.example.booksapp.model.Country;
import com.example.booksapp.model.dto.AuthorDto;
import com.example.booksapp.model.exceptions.AuthorNotFoundException;
import com.example.booksapp.model.exceptions.CountryNotFoundException;
import com.example.booksapp.repository.AuthorRepository;
import com.example.booksapp.repository.CountryRepository;
import com.example.booksapp.service.AuthorService;
import com.example.booksapp.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository, CountryService countryService, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));

        Author author = new Author(name, surname, country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));

        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author = this.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(name);
        author.setSurname(surname);

        Country country = this.countryRepository.findById(countryId).orElseThrow(() -> new CountryNotFoundException(countryId));
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto authorDto) {
        Author author = this.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        author.setName(authorDto.getName());
        author.setSurname(authorDto.getSurname());

        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(() -> new CountryNotFoundException(authorDto.getCountry()));
        author.setCountry(country);

        return Optional.of(this.authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
