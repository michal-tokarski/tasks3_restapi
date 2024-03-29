package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade trelloFacade;

    // @RequestMapping (method = RequestMethod.GET, value = "/getTrelloBoards")     // --- previous version ---
    @RequestMapping (method = RequestMethod.GET, value = "/boards")              // --- endpoint refactoring ---
    public List<TrelloBoardDto> getTrelloBoards() {

        return trelloFacade.fetchTrelloBoards();

    }

    // @RequestMapping (method = RequestMethod.POST, value = "/createTrelloCard")   // --- previous version ---
    @RequestMapping (method = RequestMethod.POST, value = "/cards")              // --- endpoint refactoring ---
    public CreatedTrelloCardDto createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return trelloFacade.createCard(trelloCardDto);

    }

}
