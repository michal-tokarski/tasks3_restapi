package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TrelloMapperTestSuite {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void test1_MapToBoards_ListOfBoardDto_To_ListOfBoard() {

        // Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board1", new ArrayList<>());
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "board2", new ArrayList<>());

        List<TrelloBoardDto> listOfTrelloBoardDtos = new ArrayList<>();
        listOfTrelloBoardDtos.add(trelloBoardDto1);
        listOfTrelloBoardDtos.add(trelloBoardDto2);

        // When
        List<TrelloBoard> mappedListOfTrelloBoards = trelloMapper.mapToBoards(listOfTrelloBoardDtos);

        // Then
        System.out.println("\n");
        System.out.println("Test : " + "test1_MapToBoards_ListOfBoardDto_To_ListOfBoard()");
        System.out.println("---------------------------");
        System.out.println("listOfTrelloBoardDtos" + " :");
        listOfTrelloBoardDtos.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("mappedListOfTrelloBoards" + " :");
        mappedListOfTrelloBoards.forEach(System.out::println);
        System.out.println("---------------------------");

        int count = 0;
        while (listOfTrelloBoardDtos.size() > count) {
            assertEquals (listOfTrelloBoardDtos.get(count).getId(), mappedListOfTrelloBoards.get(count).getId());
            assertEquals (listOfTrelloBoardDtos.get(count).getName(), mappedListOfTrelloBoards.get(count).getName());
            assertEquals (listOfTrelloBoardDtos.get(count).getLists(), mappedListOfTrelloBoards.get(count).getLists());
            count++;
        }

        assertEquals(listOfTrelloBoardDtos.size(), mappedListOfTrelloBoards.size());

    }

    @Test
    public void test2_MapToBoardsDto_ListOfBoard_To_ListOfBoardDto() {

        // Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board2", new ArrayList<>());

        List<TrelloBoard> listOfTrelloBoards = new ArrayList<>();
        listOfTrelloBoards.add(trelloBoard1);
        listOfTrelloBoards.add(trelloBoard2);

        // When
        List<TrelloBoardDto> mappedListOfTrelloBoardDtos = trelloMapper.mapToBoardsDto(listOfTrelloBoards);

        // Then
        System.out.println("\n");
        System.out.println("Test : " + "test2_MapToBoardsDto_ListOfBoard_To_ListOfBoardDto()");
        System.out.println("---------------------------");
        System.out.println("listOfTrelloBoards" + " :");
        listOfTrelloBoards.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("mappedListOfTrelloBoardDtos" + " :");
        mappedListOfTrelloBoardDtos.forEach(System.out::println);
        System.out.println("---------------------------");

        int count = 0;
        while (listOfTrelloBoards.size() > count) {
            assertEquals (listOfTrelloBoards.get(count).getId(), mappedListOfTrelloBoardDtos.get(count).getId());
            assertEquals (listOfTrelloBoards.get(count).getName(), mappedListOfTrelloBoardDtos.get(count).getName());
            assertEquals (listOfTrelloBoards.get(count).getLists(), mappedListOfTrelloBoardDtos.get(count).getLists());
            count++;
        }

        assertEquals(listOfTrelloBoards.size(), mappedListOfTrelloBoardDtos.size());

    }

    @Test
    public void test3_MapToList_ListOfListDto_To_ListOfList() {

        // Given
        TrelloListDto trelloListDto1 = new TrelloListDto("1", "list1", false);
        TrelloListDto trelloListDto2 = new TrelloListDto("2", "list2", true);

        List<TrelloListDto> listOfTrelloListDtos = new ArrayList<>();
        listOfTrelloListDtos.add(trelloListDto1);
        listOfTrelloListDtos.add(trelloListDto2);

        // When
        List<TrelloList> mappedListOfTrelloLists = trelloMapper.mapToList(listOfTrelloListDtos);

        // Then
        System.out.println("\n");
        System.out.println("Test : " + "test3_MapToList_ListOfListDto_To_ListOfList()");
        System.out.println("---------------------------");
        System.out.println("listOfTrelloListDtos" + " :");
        listOfTrelloListDtos.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("mappedListOfTrelloLists" + " :");
        mappedListOfTrelloLists.forEach(System.out::println);
        System.out.println("---------------------------");

        int count = 0;
        while (listOfTrelloListDtos.size() > count) {
            assertEquals (listOfTrelloListDtos.get(count).getId(), mappedListOfTrelloLists.get(count).getId());
            assertEquals (listOfTrelloListDtos.get(count).getName(), mappedListOfTrelloLists.get(count).getName());
            assertEquals (listOfTrelloListDtos.get(count).isClosed(), mappedListOfTrelloLists.get(count).isClosed());
            count++;
        }

        assertEquals(listOfTrelloListDtos.size(), mappedListOfTrelloLists.size());

    }

    @Test
    public void test4_MapToListDto_ListOfList_To_ListOfListDto() {

        // Given
        TrelloList trelloList1 = new TrelloList("1", "list1", false);
        TrelloList trelloList2 = new TrelloList("2", "list2", true);

        List<TrelloList> listOfTrelloLists = new ArrayList<>();
        listOfTrelloLists.add(trelloList1);
        listOfTrelloLists.add(trelloList2);

        // When
        List<TrelloListDto> mappedListOfTrelloListDtos = trelloMapper.mapToListDto(listOfTrelloLists);

        // Then
        System.out.println("\n");
        System.out.println("Test : " + "test4_MapToListDto_ListOfList_To_ListOfListDto()");
        System.out.println("---------------------------");
        System.out.println("listOfTrelloLists" + " :");
        listOfTrelloLists.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("mappedListOfTrelloListDtos" + " :");
        mappedListOfTrelloListDtos.forEach(System.out::println);
        System.out.println("---------------------------");

        int count = 0;
        while (listOfTrelloLists.size() > count) {
            assertEquals (listOfTrelloLists.get(count).getId(), mappedListOfTrelloListDtos.get(count).getId());
            assertEquals (listOfTrelloLists.get(count).getName(), mappedListOfTrelloListDtos.get(count).getName());
            assertEquals (listOfTrelloLists.get(count).isClosed(), mappedListOfTrelloListDtos.get(count).isClosed());
            count++;
        }

        assertEquals(listOfTrelloLists.size(), mappedListOfTrelloListDtos.size());

    }

    @Test
    public void test5_MapToCardDto_Card_To_CardDto() {

        // Given
        TrelloCard trelloCard1 = new TrelloCard("card1", "desc1", "pos1", "listId1");
        TrelloCard trelloCard2 = new TrelloCard("card2", "desc2", "pos2", "listId2");

        List<TrelloCard> listOfTrelloCards = new ArrayList<>();
        listOfTrelloCards.add(trelloCard1);
        listOfTrelloCards.add(trelloCard2);

        // When
        List<TrelloCardDto> mappedListOfTrelloCardDtos = new ArrayList<>();
        mappedListOfTrelloCardDtos.add(trelloMapper.mapToCardDto(listOfTrelloCards.get(0)));
        mappedListOfTrelloCardDtos.add(trelloMapper.mapToCardDto(listOfTrelloCards.get(1)));

        // Then
        System.out.println("\n");
        System.out.println("Test : " + "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(new Object () {}.getClass().getEnclosingMethod().getName());
        System.out.println("---------------------------");
        System.out.println("listOfTrelloCards" + " :");
        listOfTrelloCards.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("mappedListOfTrelloCardDtos" + " :");
        mappedListOfTrelloCardDtos.forEach(System.out::println);
        System.out.println("---------------------------");

        int count = 0;
        while (listOfTrelloCards.size() > count) {
            assertEquals (listOfTrelloCards.get(count).getName(), mappedListOfTrelloCardDtos.get(count).getName());
            assertEquals (listOfTrelloCards.get(count).getDescription(), mappedListOfTrelloCardDtos.get(count).getDescription());
            assertEquals (listOfTrelloCards.get(count).getPos(), mappedListOfTrelloCardDtos.get(count).getPos());
            assertEquals (listOfTrelloCards.get(count).getListId(), mappedListOfTrelloCardDtos.get(count).getListId());
            count++;
        }

        for (count=0 ; count < listOfTrelloCards.size() ; count++) {
            assertEquals (listOfTrelloCards.get(count).getName(), mappedListOfTrelloCardDtos.get(count).getName());
            assertEquals (listOfTrelloCards.get(count).getDescription(), mappedListOfTrelloCardDtos.get(count).getDescription());
            assertEquals (listOfTrelloCards.get(count).getPos(), mappedListOfTrelloCardDtos.get(count).getPos());
            assertEquals (listOfTrelloCards.get(count).getListId(), mappedListOfTrelloCardDtos.get(count).getListId());
        }


    }

    @Test
    public void test6_MapToCard_CardDto_To_Card() {

        // Given
        TrelloCardDto trelloCardDto1 = new TrelloCardDto("card1", "desc1", "pos1", "listId1");
        TrelloCardDto trelloCardDto2 = new TrelloCardDto("card2", "desc2", "pos2", "listId2");

        List<TrelloCardDto> listOfTrelloCardDtos = new ArrayList<>();
        listOfTrelloCardDtos.add(trelloCardDto1);
        listOfTrelloCardDtos.add(trelloCardDto2);

        // When
        List<TrelloCard> mappedListOfTrelloCards = new ArrayList<>();
        mappedListOfTrelloCards.add(trelloMapper.mapToCard(listOfTrelloCardDtos.get(0)));
        mappedListOfTrelloCards.add(trelloMapper.mapToCard(listOfTrelloCardDtos.get(1)));

        // Then
        System.out.println("\n");
        System.out.println("Test : " + "test6_MapToCard_CardDto_To_Card()");
        System.out.println("---------------------------");
        System.out.println("listOfTrelloCardDtos" + " :");
        listOfTrelloCardDtos.forEach(System.out::println);
        System.out.println("---------------------------");
        System.out.println("mappedListOfTrelloCards" + " :");
        mappedListOfTrelloCards.forEach(System.out::println);
        System.out.println("---------------------------");

        int count = 0;
        while (listOfTrelloCardDtos.size() > count) {
            assertEquals (listOfTrelloCardDtos.get(count).getName(), mappedListOfTrelloCards.get(count).getName());
            assertEquals (listOfTrelloCardDtos.get(count).getDescription(), mappedListOfTrelloCards.get(count).getDescription());
            assertEquals (listOfTrelloCardDtos.get(count).getPos(), mappedListOfTrelloCards.get(count).getPos());
            assertEquals (listOfTrelloCardDtos.get(count).getListId(), mappedListOfTrelloCards.get(count).getListId());
            count++;
        }

    }

}
