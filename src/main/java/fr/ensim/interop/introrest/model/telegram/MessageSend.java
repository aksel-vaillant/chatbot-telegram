package fr.ensim.interop.introrest.model.telegram;

import org.springframework.beans.factory.annotation.Value;

public class MessageSend {

    public final String chat_id = "935926668";
    public String text;

    public String getChat_id() {
        return chat_id;
    }

    public String getText() {
        return text;
    }

    public MessageSend(){}
    public MessageSend(String text){
        this.text = text;
    }
}
