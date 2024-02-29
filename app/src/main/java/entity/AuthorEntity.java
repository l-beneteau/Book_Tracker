package entity;


import jakarta.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class AuthorEntity {
    private int id;
    private String name;

}
