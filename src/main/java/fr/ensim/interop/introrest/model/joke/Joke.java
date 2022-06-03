package fr.ensim.interop.introrest.model.joke;

public class Joke {
    private int id;
    private String title;
    private String text;
    private int note;

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
    public int getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setNote(int note) {
        this.note = note;
    }

    public Joke(){}

    public Joke(int id, String title, String text, int note){
        this.id = id;
        this.title = title;
        this.text = text;
        this.note = note;
    }

    @Override
    public String toString() {
        return "Blague du jour!\n" + getText();
    }


}
