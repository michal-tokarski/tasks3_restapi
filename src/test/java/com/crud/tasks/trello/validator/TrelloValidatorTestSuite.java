package com.crud.tasks.trello.validator;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.mapper.TrelloMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;
import static org.junit.Assert.assertEquals;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@RunWith(MockitoJUnitRunner.class)
public class TrelloValidatorTestSuite {

    @InjectMocks
    private TrelloValidator trelloValidator;

    /*
    @Test
    public void test1_validateCard(){

        // Get Logback Logger
        Logger LOGGER = (Logger) LoggerFactory.getLogger(TrelloValidator.class);

        // Create and start a ListAppender
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();

        // Add the appender to the logger
        LOGGER.addAppender(listAppender); // <=== addAppender not working !!!

        // Given
        TrelloCard trelloCard_test = new TrelloCard("TrelloCard test", "Description","top", "01");
        TrelloCard trelloCard2_regular = new TrelloCard("TrelloCard regular", "Description","bottom", "02");

        // When
        TrelloValidator validator = new TrelloValidator();
        validator.validateCard(trelloCard_test);
        validator.validateCard(trelloCard2_regular);

        // Then
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals ("Someone is testing my application", logsList.get(0).getMessage());
        assertEquals ("Seems that my application is used in proper way", logsList.get(1).getMessage());

    }
    */

    @Test
    public void test2_validateTrelloBoards(){

        // Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        List<TrelloList> trelloLists = new ArrayList<>();

        TrelloList list = new TrelloList("Id 1", "Test", false);
        trelloLists.add(list);

        TrelloBoard board1 = new TrelloBoard("Id 1", "Board 1", trelloLists);
        TrelloBoard board2 = new TrelloBoard("Id 2", "Board 2", trelloLists);
        trelloBoards.add(board1);
        trelloBoards.add(board2);

        TrelloValidator validator = new TrelloValidator();

        // When
        List<TrelloBoard> filteredBoards = validator.validateTrelloBoards(trelloBoards);

        // Then : Assert #1 :
        for (int count=0 ; count < trelloBoards.size() ; count++) {
            assertEquals (trelloBoards.get(count).getId(), filteredBoards.get(count).getId());
            assertEquals (trelloBoards.get(count).getName(), filteredBoards.get(count).getName());
            assertEquals (trelloBoards.get(count).getLists(), filteredBoards.get(count).getLists());
        }

        // Then : Assert #2 :
        assertEquals(trelloBoards.size(), filteredBoards.size());

    }

}
