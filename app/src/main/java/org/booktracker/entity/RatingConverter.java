package org.booktracker.entity;

import org.booktracker.model.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RatingConverter implements AttributeConverter<Rating, String> {

    @Override
    public String convertToDatabaseColumn(Rating attribute) {
        if (attribute == null)
            return null;
        return switch (attribute) {
            case UNFINISHED -> "UNFINISHED";
            case MEH -> "MEH";
            case GOOD -> "GOOD";
            case WONDERFUL -> "WONDERFUL";
            default -> throw new IllegalArgumentException(attribute + " not supported.");
        };
    }

    @Override
    public Rating convertToEntityAttribute(String dbData) {
        if (dbData == null)
            return null;
        return switch(dbData) {
            case "UNFINISHED" -> Rating.UNFINISHED;
            case "MEH" -> Rating.MEH;
            case "GOOD" -> Rating.GOOD;
            case "WONDERFUL" -> Rating.WONDERFUL;
            default -> throw new IllegalArgumentException(dbData + " not supported.");
        };
    }
}
