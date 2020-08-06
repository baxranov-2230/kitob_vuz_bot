package com.company.controller;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.LinkedList;
import java.util.List;

public class MainController extends TelegramLongPollingBot {
    private GeneralController generalController;

    public MainController() {
        this.generalController = new GeneralController();
    }

    public void onUpdateReceived(Update update) {

        // System.out.println(update);
        SendMessage sendMessage = new SendMessage();
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();

            String date = callbackQuery.getData();
            User user = callbackQuery.getFrom();
            Message message = callbackQuery.getMessage();

            if (date.equals("menu")) {
                EditMessageText editMessageText = new EditMessageText();


                editMessageText.setText("siz menu buttonni click qildingiz");
                editMessageText.setChatId(message.getChatId());
                editMessageText.setMessageId(message.getMessageId());

                //Second
                List<InlineKeyboardButton> secondRow = new LinkedList();
                secondRow.add(new InlineKeyboardButton().setText("secondTest").setCallbackData("Test2"));

                //Row collection
                List<List<InlineKeyboardButton>> rowCollection = new LinkedList();
                rowCollection.add(secondRow);

                //keyboard
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(rowCollection);

                editMessageText.setReplyMarkup(inlineKeyboardMarkup);

                try {
                    execute(editMessageText);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else {

            Message message = update.getMessage();
            sendMessage.setChatId(message.getChatId());

            Integer messageId = message.getMessageId();
            String text = message.getText();
            User user = message.getFrom();


            if (text.equals("/start") || text.equals("/help") || text.equals("/setting")) {
                sendMessage = this.generalController.handle(text, message.getChatId(), messageId);

            } else {
                sendMessage.setText("buyruq mavjud emas");
            }


            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }
    }

    public String getBotUsername() {
        return "kitob_vuz_bot";
    }

    public String getBotToken() {
        return "1316554619:AAGJmAl8T6Jt7rpCBLq3hYK4O0pZBLbtJxg";
    }
}
