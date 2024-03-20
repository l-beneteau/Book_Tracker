package org.booktracker.response;

import lombok.Data;
import org.booktracker.model.GroupType;
import org.booktracker.model.Stat;

@Data
public class StatResponse<T> {
    GroupType groupType;
    T groupValue;
    Stat stat;
}
