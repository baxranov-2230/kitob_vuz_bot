package com.company.controller;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MainController extends TelegramLongPollingBot {

    public void onUpdateReceived(Update update) {
       // System.out.println(update);

        Message message=update.getMessage();

        Integer messageId=message.getMessageId();
        String text=message.getText();
        User user=message.getFrom();

        SendMessage sendMessage=new SendMessage();
        sendMessage.setChatId(message.getChatId());
        sendMessage.setText("salom");

        if (text.equals("/start")){
            sendMessage.setText("Salom xush kelibsiz");
        } else {
            sendMessage.setText("buyruq mavjud emas");
        }


        try {
            execute(sendMessage);
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
