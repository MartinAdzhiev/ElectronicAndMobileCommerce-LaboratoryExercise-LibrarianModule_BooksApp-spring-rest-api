package com.example.booksapp.config;

import com.example.booksapp.model.enumerations.Category;
import com.example.booksapp.service.AuthorService;
import com.example.booksapp.service.BookService;
import com.example.booksapp.service.CountryService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public DataInitializer(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @PostConstruct
    public void initData() {
        this.countryService.save("Macedonia", "Europe");
        this.countryService.save("China", "Asia");
        this.countryService.save("Serbia", "Europe");
        this.countryService.save("UK", "Europe");
        this.countryService.save("Germany", "Europe");
        this.countryService.save("Russia", "Asia");
        this.countryService.save("USA", "North America");
        this.countryService.save("Nigeria", "Africa");

        this.authorService.save("Blaze", "Koneski", Long.parseLong("1"));
        this.authorService.save("Ji", "Xyong", Long.parseLong("2"));
        this.authorService.save("Ivo", "Andric", Long.parseLong("3"));
        this.authorService.save("William", "Shakespeare", Long.parseLong("4"));
        this.authorService.save("Friedrich", "Nietzsche", Long.parseLong("5"));
        this.authorService.save("Leo", "Tolstoy", Long.parseLong("6"));
        this.authorService.save("Mark", "Twain", Long.parseLong("7"));
        this.authorService.save("Chinua", "Achebe", Long.parseLong("8"));

        this.bookService.save("Nebesna reka", Category.NOVEL, Long.parseLong("1"), 10);
        this.bookService.save("Na Drini ćuprija", Category.NOVEL, Long.parseLong("3"), 10);
        this.bookService.save("Prokleta avlija", Category.NOVEL, Long.parseLong("3"), 10);
        this.bookService.save("Hamlet", Category.NOVEL, Long.parseLong("4"), 10);
        this.bookService.save("Romeo and Juliet", Category.NOVEL, Long.parseLong("4"), 10);
        this.bookService.save("Othello", Category.DRAMA, Long.parseLong("4"), 10);
        this.bookService.save("Julius Caesar", Category.HISTORY, Long.parseLong("4"), 10);
        this.bookService.save("Thus Spoke Zarathustra", Category.FANTASY, Long.parseLong("5"), 10);
        this.bookService.save("The Antichrist", Category.NOVEL, Long.parseLong("5"), 10);
        this.bookService.save("War and Peace", Category.CLASSICS, Long.parseLong("6"), 10);
        this.bookService.save("Ana Karenina", Category.NOVEL, Long.parseLong("6"), 10);
        this.bookService.save("The Death of Ivan Ilyich", Category.NOVEL, Long.parseLong("6"), 10);
        this.bookService.save("The Adventures of Tom Sawyer", Category.NOVEL, Long.parseLong("7"), 10);
        this.bookService.save("Adventures of Huckleberry Finn", Category.NOVEL, Long.parseLong("7"), 10);
        this.bookService.save("Life on the Mississippi", Category.BIOGRAPHY, Long.parseLong("7"), 10);
        this.bookService.save("The Mysterious Stranger", Category.NOVEL, Long.parseLong("7"), 10);
        this.bookService.save("Things Fall Apart", Category.NOVEL, Long.parseLong("8"), 10);
        this.bookService.save("Arrow of God", Category.NOVEL, Long.parseLong("8"), 10);
        this.bookService.save("There Was a Country", Category.HISTORY, Long.parseLong("8"), 10);
    }
}
