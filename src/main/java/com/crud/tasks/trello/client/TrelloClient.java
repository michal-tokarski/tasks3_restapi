package com.crud.tasks.trello.client;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.config.TrelloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class TrelloClient {

    private final static Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    /*
    // Original version, moved later to TrelloConfig class :
    // -------------------------------------------------------
    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String trelloUsername;
     */

    @Autowired
    private TrelloConfig trelloConfig;

    @Autowired
    private RestTemplate restTemplate;

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = getTrelloBoardsUri();

        // 1st version :
        /* TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
        if (boardsResponse != null) {
            return Arrays.asList(boardsResponse);
        }
        return new ArrayList<>(); */

        // 2nd version :
        try {
            TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
            return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }

    }

    private URI getTrelloBoardsUri() {


        URI uri = UriComponentsBuilder.fromHttpUrl(
                trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getTrelloUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .queryParam("lists", "all")
                .build()
                .encode()
                .toUri();
        System.out.println(uri);
        return uri;

    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto) {
        URI url = UriComponentsBuilder.fromHttpUrl(
                trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();

        return restTemplate.postForObject(url, null, CreatedTrelloCard.class);

    }

}
