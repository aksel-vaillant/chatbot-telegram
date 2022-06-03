package fr.ensim.interop.introrest.model.telegram;

public class MessageSend {

    public String chat_id;
    public String text;

    public String getText() {
        return text;
    }
    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public MessageSend(String text){
        this.text = text;
    }
}
