package org.booktracker.service;

import org.booktracker.exception.BookNotFoundException;
import org.booktracker.model.*;
import org.booktracker.parameter.BookParameter;
import org.booktracker.parameter.StatParameter;
import org.booktracker.response.StatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatService {

    @Autowired
    BookService bookService;


    public List<StatResponse<?>> getStatWithParameter(StatParameter statParameter) throws BookNotFoundException {
        BookParameter bookParameter = new BookParameter();
        bookParameter.setRead(true);
        bookParameter.setSeries(statParameter.getSeries());
        bookParameter.setGenre(statParameter.getGenre());
        bookParameter.setRating(statParameter.getRating());
        List<StatResponse<?>> statResponses = new ArrayList<>();
        if(statParameter.getGroupType()==null){
            StatResponse<Genre> statResponse = new StatResponse<>();
            statResponse.setStat(new Stat(bookService.findBooks(bookParameter)));
            statResponses.add(statResponse);
            return statResponses;
        }
        return switch (statParameter.getGroupType()) {
            case GENRE -> {
                for (Genre genre : Genre.values()) {
                    StatResponse<Genre> statResponse = new StatResponse<>();
                    statResponse.setGroupType(GroupType.GENRE);
                    statResponse.setGroupValue(genre);
                    bookParameter.setGenre(genre);
                    statResponse.setStat(new Stat(bookService.findBooks(bookParameter)));
                    statResponses.add(statResponse);
                }
                yield statResponses;
            }
            case RATING -> {
                for (Rating rating : Rating.values()) {
                    StatResponse<Rating> statResponse = new StatResponse<>();
                    statResponse.setGroupType(GroupType.RATING);
                    statResponse.setGroupValue(rating);
                    bookParameter.setRating(rating);
                    statResponse.setStat(new Stat(bookService.findBooks(bookParameter)));
                    statResponses.add(statResponse);
                }
                yield statResponses;
            }
        };
    }

}