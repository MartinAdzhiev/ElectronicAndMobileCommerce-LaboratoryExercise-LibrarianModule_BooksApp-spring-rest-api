package com.example.booksapp.web.rest;

import com.example.booksapp.model.enumerations.Category;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoryRestController {

    @RequestMapping
    public List<Category> findAllCategories() {
        return List.of(Category.class.getEnumConstants());
    }
}
