package com.example.booksapp.web.controller;

import com.example.booksapp.model.Book;
import com.example.booksapp.model.enumerations.Category;
import com.example.booksapp.service.AuthorService;
import com.example.booksapp.service.BookService;
import com.example.booksapp.service.CountryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CountryService countryService;

    public BookController(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @GetMapping({"/", "/books"})
    public String showList(Model model) {
        List<Book> books = this.bookService.listAll();

        model.addAttribute("books", books);

        return "list";
    }

    @GetMapping(path = {"/books/add"})
    public String showAdd(Model model) {
        model.addAttribute("authors", this.authorService.findAll() );
        model.addAttribute("categories", Category.values());
        return "add";
    }

    @GetMapping(path = {"/books/{id}/edit"})
    public String showEdit(@PathVariable Long id, Model model) {
        model.addAttribute("authors", this.authorService.findAll() );
        model.addAttribute("categories", Category.values());
        model.addAttribute("book", this.bookService.findById(id));
        return "add";
    }

    @PostMapping(path = {"/books/"})
    public String create(@RequestParam String name,
                         @RequestParam Integer availableCopies,
                         @RequestParam Category category,
                         @RequestParam Long authorId) {
        this.bookService.save(name, category, authorId, availableCopies);
        return "redirect:/books";
    }

    @PostMapping(path = {"/books/{id}"})
    public String update(@PathVariable Long id,
                         @RequestParam String name,
                         @RequestParam Integer availableCopies,
                         @RequestParam Category category,
                         @RequestParam Long authorId) {
        this.bookService.edit(id, name, category, authorId, availableCopies);
        return "redirect:/books";
    }

//    @PostMapping("/books")
//    public String saveProduct(
//            @PathVariable(required = false) Long id,
//            @RequestParam String name,
//            @RequestParam Integer availableCopies,
//            @RequestParam Category category,
//            @RequestParam Long authorId) {
//        if (id != null) {
//            this.bookService.edit(id, name, category, authorId, availableCopies);
//        } else {
//            this.bookService.save(name, category, authorId, availableCopies);
//        }
//        return "redirect:/books";
//    }


    @PostMapping("/books/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.bookService.deleteById(id);
        return "redirect:/books";
    }

    @PostMapping("/books/{id}/markAsTaken")
    public String markAsTaken(@PathVariable Long id) {
        this.bookService.markAsTaken(id);

        return "redirect:/books";
    }
}
