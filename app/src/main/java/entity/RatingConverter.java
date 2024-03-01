package entity;

import back.Rating;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class RatingConverter implements AttributeConverter<Rating, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Rating attribute) {
        if (attribute == null)
            return null;
        return switch (attribute) {
            case UNFINISHED -> 0;
            case MEH -> 1;
            case GOOD -> 2;
            case WONDERFUL -> 3;
            default -> throw new IllegalArgumentException(attribute + " not supported.");
        };
    }

    @Override
    public Rating convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;
        return switch(dbData) {
            case 0 -> Rating.UNFINISHED;
            case 1 -> Rating.MEH;
            case 2 -> Rating.GOOD;
            case 3 -> Rating.WONDERFUL;
            default -> throw new IllegalArgumentException(dbData + " not supported.");
        };
    }
}
