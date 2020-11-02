package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    // private TrelloClient trelloClient; // (trelloClient replaced by trelloService)
    private TrelloService trelloService;

    // 1st version
    /*
    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public void getTrelloBoards() {

        // GET request
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();

        trelloBoards = trelloBoards.stream()
            .filter(trelloBoardDto -> trelloBoardDto.getId() != null)
            .filter(trelloBoardDto -> trelloBoardDto.getName() != null)
            // .filter(Objects::nonNull)
            // .filter(trelloBoardDto -> trelloBoardDto.getName().contains("Kodilla"))
            .collect(Collectors.toList());

        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getName() + " - " + trelloBoardDto.getId());

            System.out.println("This board contains lists:");
            trelloBoardDto.getLists().forEach(trelloList ->
                System.out.println(trelloList.getName() + " - " + trelloList.getId() + " - " + trelloList.isClosed()));
        });

    }
     */

    // 2nd version
    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        /* return trelloClient.getTrelloBoards(); // (trelloClient replaced by trelloService) */
        return trelloService.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCard createdTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        /* return trelloClient.createNewCard(trelloCardDto); // (trelloClient replaced by trelloService) */
        return trelloService.createTrelloCard(trelloCardDto);
    }

}
