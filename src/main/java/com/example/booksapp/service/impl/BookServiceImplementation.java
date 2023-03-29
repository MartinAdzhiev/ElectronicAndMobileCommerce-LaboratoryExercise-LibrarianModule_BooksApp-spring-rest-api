package com.example.booksapp.service.impl;

import com.example.booksapp.model.Author;
import com.example.booksapp.model.Book;
import com.example.booksapp.model.dto.BookDto;
import com.example.booksapp.model.enumerations.Category;
import com.example.booksapp.model.exceptions.AuthorNotFoundException;
import com.example.booksapp.model.exceptions.BookNotFoundException;
import com.example.booksapp.repository.AuthorRepository;
import com.example.booksapp.repository.BookRepository;
import com.example.booksapp.service.BookService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }


    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> save(String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));

        Book book = new Book(name, category, author, availableCopies);


        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer availableCopies) {
        Author author = this.authorRepository.findById(authorId).orElseThrow(() -> new AuthorNotFoundException(authorId));
        Book book = this.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);


        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author = this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        }

        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
