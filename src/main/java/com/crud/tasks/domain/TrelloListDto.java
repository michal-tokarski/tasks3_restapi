
package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloListDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("closed")
    private boolean isClosed;

    /* NoArgs constructor commented out due to @NoArgsConstructor above
    public TrelloListDto() {}
    */

    @Override
    public String toString() {
        return "TrelloListDto {" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", isClosed=" + isClosed +
                '}';
    }
}