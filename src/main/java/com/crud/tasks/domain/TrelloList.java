package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TrelloList {

    private String id;
    private String name;
    private boolean isClosed;

    @Override
    public String toString() {
        return "TrelloList {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isClosed=" + isClosed +
                '}';
    }
}
