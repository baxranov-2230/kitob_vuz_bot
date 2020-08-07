package com.company.controller;

import com.company.dto.CodeMessage;
import com.company.util.InlineButtonUtil;
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

        SendMessage sendMessage = new SendMessage();
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();

            String data = callbackQuery.getData();
            User user = callbackQuery.getFrom();
            Message message = callbackQuery.getMessage();

            if (data.equals("menu")) {
                this.sendMsg(this.generalController.handle(data, message.getChatId(), message.getMessageId()));
            }
        } else {

            Message message = update.getMessage();
            sendMessage.setChatId(message.getChatId());

            Integer messageId = message.getMessageId();
            String text = message.getText();
            User user = message.getFrom();


            if (text.equals("/start") || text.equals("/help") || text.equals("/setting")) {
                this.sendMsg(this.generalController.handle(text, message.getChatId(), messageId));

            } else {
                sendMessage.setText("buyruq mavjud emas");
                this.sendMsg(sendMessage);
            }


        }
    }

    public void sendMsg(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(CodeMessage codeMessage) {
        try {
            switch (codeMessage.getType()) {
                case MESSAGE:
                    execute(codeMessage.getSendMessage());
                    break;
                case EDIT:
                    execute(codeMessage.getEditMessageText());
                    break;
                default:
                    break;
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "kitob_vuz_bot";
    }

    public String getBotToken() {
        return "1316554619:AAGJmAl8T6Jt7rpCBLq3hYK4O0pZBLbtJxg";
    }
}
