package org.booktracker.controller;

import org.booktracker.exception.BookNotFoundException;
import org.booktracker.model.Stat;
import org.booktracker.parameter.StatParameter;
import org.booktracker.response.StatResponse;
import org.booktracker.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("stat")
public class StatController {

    @Autowired
    StatService statService;

    @GetMapping
    public List<StatResponse<?>> getStatParameter(StatParameter statParameter){
        try{
            return statService.getStatWithParameter(statParameter);
        } catch (BookNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

}
