
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class AzerBot extends TelegramLongPollingBot {
    final private String BOT_NAME = "AzerBot";
    final private String BOT_TOKEN = "5451112923:AAGBY89zMAwEuVd18IICnPgTgcxgo5myLsA";

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    List<String> messages = new ArrayList<>();
    AzerBot(){initKeyboard();}


    @Override
    public String getBotUsername(){
        return BOT_NAME;
    }

    @Override
    public String getBotToken(){
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update){
        try {
            if(update.hasMessage() && update.getMessage().hasText()){
                String chatId = update.getMessage().getChatId().toString();
                String response = parseMessage(update.getMessage().getText());

                SendMessage outMess = new SendMessage();
                outMess.setChatId(chatId);
                outMess.setText(response);
                outMess.setReplyMarkup(replyKeyboardMarkup);

                execute(outMess);
            }
        }   catch (TelegramApiException e)  {
            e.printStackTrace();
        }
    }

    public String parseMessage(String text){
        String response;
        if(text.equals("/help") || text.equals("Azərbaycan"))
            response =  "aleeee sizi azerbaycan botu alqışlayır"+
                        " eee!!! ən son mesajı almaq üçün /getlast,"+
                        " təsadüfi mesaj almaq üçün /getrandom"+
                        " düyməsini basın eeeeeeeeee";
        else if(text.equals("/getrandom")) {
            int index = new Random().nextInt(messages.size());
            response = messages.get(index);
        }
        else if(text.equals("/getlast"))
            response = messages.get(messages.size()-1);
        else{
            response = text;
            messages.add(text);
        }

        return response;
    }

    void initKeyboard(){
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton("Azərbaycan"));
        keyboardRows.add(keyboardRow);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }

}
