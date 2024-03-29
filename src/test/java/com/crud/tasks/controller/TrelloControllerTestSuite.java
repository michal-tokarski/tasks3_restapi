package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.facade.TrelloFacade;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrelloController.class)
class TrelloControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrelloFacade trelloFacade;

    @Test
    public void test1_shouldFetchEmptyTrelloBoards() throws Exception {

        // Given
        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        // When & Then
        mockMvc
            .perform(
                // get("/v1/trello/getTrelloBoards")       // --- previous version ---
                get("/v1/trello/boards")         // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().is(200)) // or isOk()
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void test2_shouldFetchTrelloBoards() throws Exception {

        // Given
        List<TrelloListDto> trelloLists = new ArrayList<>();
        trelloLists.add(new TrelloListDto("1", "Test List", false));

        List<TrelloBoardDto> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoardDto("1", "Test Task", trelloLists));

        when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);

        // When & Then
        mockMvc
            .perform(
                // get("/v1/trello/getTrelloBoards")          // --- previous version ---
                get("/v1/trello/boards")            // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk())
                // Trello board fields
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is("1")))
                .andExpect(jsonPath("$[0].name", is("Test Task")))
                // Trello list fields
                .andExpect(jsonPath("$[0].lists", hasSize(1)))
                .andExpect(jsonPath("$[0].lists[0].id", is("1")))
                .andExpect(jsonPath("$[0].lists[0].name", is("Test List")))
                .andExpect(jsonPath("$[0].lists[0].closed", is(false)));

    }

    @Test
    public void test3_shouldCreateTrelloCard() throws Exception {

        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "Test", "Test description", "top", "1");

        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "323", "Test", "http://test.com",
                new Badges(666, new AttachmentsByType(new Trello(777,888)))
        );

        // behavior of 'trelloFacade' mock when the 'createCard' method is used, having a 'TrelloCardDto' as parameter
        when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDto.class))).thenReturn(createdTrelloCardDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(trelloCardDto);

        // When & Then
        mockMvc
            .perform(
                // post("/v1/trello/createTrelloCard")       // --- previous version ---
                post("/v1/trello/cards")           // --- endpoint refactoring ---
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent)
            )
                .andExpect(jsonPath("$.id", is("323")))
                .andExpect(jsonPath("$.name", is("Test")))
                .andExpect(jsonPath("$.shortUrl", is("http://test.com")));

    }

}