package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private SimpleEmailService simpleEmailService;


    @Test
    public void test1_fetchTrelloBoards() {

        // Given
        List<TrelloListDto> trelloList = new ArrayList<>();
        trelloList.add(new TrelloListDto("1", "Trello List Dto 666", false));

        List<TrelloBoardDto> trelloBoard = new ArrayList<>();
        trelloBoard.add(new TrelloBoardDto("222", "Trello Board Dto 77777", trelloList));

        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoard);

        // When
        List<TrelloBoardDto> trelloBoardDtoList = trelloService.fetchTrelloBoards();

        // Then
        Assert.assertEquals("222", trelloBoardDtoList.get(0).getId());
        Assert.assertEquals("Trello List Dto 666", trelloBoardDtoList.get(0).getLists().get(0).getName());

    }

    @Test
    public void test2_createTrelloCard() {

        // Given
        TrelloCardDto trelloCardDto = new TrelloCardDto(
                "name", "desc", "pos", "1");
        CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
                "dummy id",
                "dummy name",
                "dummy url",
                new Badges(15, new AttachmentsByType(new Trello(33,67)))
        );

        when(trelloService.createTrelloCard(trelloCardDto)).thenReturn(createdTrelloCardDto);

        // When
        CreatedTrelloCardDto resultCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);

        // Then
        Assert.assertEquals("dummy id", resultCreatedTrelloCardDto.getId());
        Assert.assertEquals("dummy name", resultCreatedTrelloCardDto.getName());
        Assert.assertEquals("dummy url", resultCreatedTrelloCardDto.getShortUrl());

        // Assert.assertEquals(new Badges(15,new AttachmentsByType(new Trello(33,67))), resultCreatedTrelloCardDto.getBadges());

        Assert.assertEquals(createdTrelloCardDto.getId(), resultCreatedTrelloCardDto.getId());
        Assert.assertEquals(createdTrelloCardDto.getName(), resultCreatedTrelloCardDto.getName());
        Assert.assertEquals(createdTrelloCardDto.getShortUrl(), resultCreatedTrelloCardDto.getShortUrl());
        Assert.assertEquals(createdTrelloCardDto.getBadges(), resultCreatedTrelloCardDto.getBadges());

    }

}
