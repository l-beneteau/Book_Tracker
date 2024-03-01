package entity;


import jakarta.persistence.Entity;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@RequiredArgsConstructor
@Getter
@Table(name = "Author")
public class AuthorEntity {
    @NonNull private int id;
    @NonNull private String name;

    @ManyToMany(mappedBy = "projects")
    private final Set<BookEntity> books = new HashSet<>();

}
