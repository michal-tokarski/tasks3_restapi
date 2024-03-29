package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.crud.tasks.service.MailTemplate.MAIL_TEMPLATE__CREATED_TRELLO_CARD_MAIL;

@Service
public class TrelloService {

    private static final String SUBJECT = "Tasks: New Trello card";

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private TrelloClient trelloClient;

    @Autowired
    private SimpleEmailService emailService;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        return trelloClient.getTrelloBoards();
    }

    public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {

        CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);

        Optional.ofNullable(newCard).ifPresent(card ->
            emailService.send(
                new Mail(
                    adminConfig.getAdminMail(),
                    SUBJECT,
                    "New card:" + " [ " + newCard.getName() + " ] " + "has been created on your Trello account.",
                    MAIL_TEMPLATE__CREATED_TRELLO_CARD_MAIL
                )
            )
        );

        return newCard;
    }


}
