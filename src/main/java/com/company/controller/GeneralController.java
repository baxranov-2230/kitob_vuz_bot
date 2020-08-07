package com.company.controller;

import com.company.dto.CodeMessage;
import com.company.enums.CodeMessageType;
import com.company.util.InlineButtonUtil;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.LinkedList;
import java.util.List;

public class GeneralController {

    public CodeMessage handle(String text, Long chatId, Integer messageId) {

        CodeMessage codeMessage = new CodeMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        codeMessage.setSendMessage(sendMessage);

        if (text.equals("/start")) {
            sendMessage.setText("Assalomu alaykum");
            sendMessage.setParseMode("Markdown");


            sendMessage.setReplyMarkup(InlineButtonUtil.keyboard(
                    InlineButtonUtil.collection(
                            InlineButtonUtil.row(
                                    InlineButtonUtil.button("Go to menu", "menu"))
                    )

            ));
            codeMessage.setType(CodeMessageType.MESSAGE);

//            InlineKeyboardButton menu = InlineButtonUtil.button("Go to menu", "menu");
//
//            //row
//            List<InlineKeyboardButton> row = InlineButtonUtil.row(menu);
//
//
//
//
//            //Row collection
//            List<List<InlineKeyboardButton>> rowCollection = InlineButtonUtil.collection(row);
//
//
//            sendMessage.setReplyMarkup(InlineButtonUtil.keyboard(rowCollection));
        } else if (text.equals("/help")) {
            String msg = "*TodoList* Yordam oynasi.\n Siz bu bo'tda qilish kerak bo'lgna islariz jadvalini tuzishingiz mumkin.\n" +
                    "malumot uchun videoni [inline URL](https://www.youtube.com/channel/UCFoy0KOV9sihL61PJSh7x1g)  ko'ring.\n"
                    + "Yokiy manabu videoni ko'ring ";
            sendMessage.setText(msg);
            sendMessage.setParseMode("Markdown");
            sendMessage = sendMessage.disableWebPagePreview();
            codeMessage.setSendMessage(sendMessage);
            codeMessage.setType(CodeMessageType.MESSAGE);

        } else if (text.equals("/setting")) {
            sendMessage.setText("setting hali mavjud emas");
            sendMessage.setParseMode("Markdown");
            codeMessage.setType(CodeMessageType.MESSAGE);

        } else if (text.equals("menu")) {
            EditMessageText editMessageText = new EditMessageText();

            editMessageText.setText("*Asosiy menu*");
            editMessageText.setChatId(chatId);
            editMessageText.setMessageId(messageId);
            sendMessage.setParseMode("Markdown");

            editMessageText.setReplyMarkup(InlineButtonUtil.keyboard(
                    InlineButtonUtil.collection(
                            InlineButtonUtil.row(InlineButtonUtil.button("ToDo List", "todo/list")),
                            InlineButtonUtil.row(InlineButtonUtil.button("Create New", "todo/create"))
                    )));
            codeMessage.setType(CodeMessageType.EDIT);
            codeMessage.setEditMessageText(editMessageText);
        }

        return codeMessage;
    }
}
