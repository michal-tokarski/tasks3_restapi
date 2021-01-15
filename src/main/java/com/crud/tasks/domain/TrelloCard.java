package com.crud.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TrelloCard {

    private String name;
    private String description;
    private String pos;
    private String listId;

    @Override
    public String toString() {
        return "TrelloCard {" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pos='" + pos + '\'' +
                ", listId='" + listId + '\'' +
                '}';
    }
}
