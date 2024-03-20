package org.booktracker.model;

import lombok.Data;

import java.util.List;

@Data
public class Stat {
    private int nbBook;
    private int nbPage;

    public Stat(List<Book> books){
        nbBook = books.size();
        nbPage = 0;
        for (Book book : books){
            nbPage += book.getPages();
        }
    }
}
