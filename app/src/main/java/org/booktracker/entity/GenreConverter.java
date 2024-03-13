package org.booktracker.entity;

import jakarta.persistence.AttributeConverter;
import org.booktracker.model.Genre;

public class GenreConverter implements AttributeConverter<Genre, String> {
    @Override
    public String convertToDatabaseColumn(Genre attribute) {
        if (attribute == null)
            return null;
        return switch (attribute) {
            case FANTASY -> "Fantasy";
            case SCIENCE_FICTION -> "Science Fiction";
            case DOCUMENTARY -> "Documentary";
            case POPULARIZATION -> "Popularization";
            case COMIC_BOOK -> "Comic Book";
            case FANTASTIC -> "Fantastic";
            case DETECTIVE_NOVEL -> "Detective Novel";
            default -> throw new IllegalArgumentException(attribute + " not supported.");
        };
    }

    @Override
    public Genre convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return switch(dbData) {
            case "Fantasy" -> Genre.FANTASY;
            case "Science Fiction" -> Genre.SCIENCE_FICTION;
            case "Documentary" -> Genre.DOCUMENTARY;
            case "Popularization" -> Genre.POPULARIZATION;
            case "Comic Book" -> Genre.COMIC_BOOK;
            case "Fantastic" -> Genre.FANTASTIC;
            case "Detective Novel" -> Genre.DETECTIVE_NOVEL;
            default -> throw new IllegalArgumentException(dbData + " not supported.");
        };
    }
}
