package com.example.booksapp.model.enumerations;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape=JsonFormat.Shape.OBJECT)
public enum Category {
    NOVEL("NOVEL"),
    THRILER("THRILER"),
    HISTORY("HISTORY"),
    FANTASY("FANTASY"),
    BIOGRAPHY("BIOGRAPHY"),
    CLASSICS("CLASSICS"),
    DRAMA("DRAMA");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getName() {
        return name();
    }
}
