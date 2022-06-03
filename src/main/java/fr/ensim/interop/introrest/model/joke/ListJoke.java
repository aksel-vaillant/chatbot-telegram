package fr.ensim.interop.introrest.model.joke;

import java.util.ArrayList;

public class ListJoke {

    private static ArrayList<Joke> listJokes = new ArrayList<Joke>();

    public static ArrayList<Joke> returnJokes(){
        listJokes.add(new Joke(listJokes.size(), "Tu connais?", "Tu connais la blague de la chaise? Elle est pliante.",3));
        listJokes.add(new Joke(listJokes.size(), "Un mec gifle un aveugle", "Un mec gifle un aveugle : « Tu l’avais pas vu venir celle-là ?! »",6));
        listJokes.add(new Joke(listJokes.size(), "Un chinois sur une mobylette", "Que fait un chinois sur une mobylette ? Nèèèèmmmmm, Nèèèèmmmmm, Nèèèèmmmmm",2));
        listJokes.add(new Joke(listJokes.size(), "Le bossu sans bras", "Qu’est ce qu’un bossu sans bras et sans jambe ? Une madeleine",7));
        listJokes.add(new Joke(listJokes.size(), "Le rat et la queue coupée", "Qu’est-ce qu’un rat avec la queue coupée ? Un rat-courci.",4));

        return listJokes;
    }
}
