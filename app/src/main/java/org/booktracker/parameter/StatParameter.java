package org.booktracker.parameter;

import lombok.Data;
import org.booktracker.model.Genre;
import org.booktracker.model.GroupType;
import org.booktracker.model.Rating;

import java.util.Date;

@Data
public class StatParameter {
    private String series;
    private Genre genre;
    private Rating rating;
    private GroupType groupType;
}
