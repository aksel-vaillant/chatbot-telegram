package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.joke.Joke;
import fr.ensim.interop.introrest.model.joke.ListJoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;

@RestController
public class JokeRestController {
    @Autowired
    private MessageRestController messageRestController;

    private ArrayList<Joke> listJokes = ListJoke.returnJokes();

    //Opérations sur la ressource getJoke
    @GetMapping("/getJoke")
    public ResponseEntity<Joke> getJoke(
            @RequestParam (name = "sendingMessage", required = false, defaultValue = "false") boolean sendingMessage
    ){
        // Pick a joke and send it to the client
        Collections.shuffle(listJokes);
        Joke joke = listJokes.get(0);

        // Get the object from sendMessage before sending it
        if(sendingMessage)
            messageRestController.sendMessage(joke.toString());

        return ResponseEntity.ok().body(joke);
    }

    @GetMapping("/getJokeById")
    public ResponseEntity<Joke> getJokeById(
            @RequestParam (name = "sendingMessage", required = false, defaultValue = "false") boolean sendingMessage,
            @RequestParam int id
    ){
        // Pick the good joke and send it to the client
        Joke joke = new Joke();
        for(Joke j : listJokes){
            if(j.getId() == id){
                joke = j;
            }
        }

        // Means it didn"t gave any joke
        if(joke.getText() == null){
            return ResponseEntity.badRequest().build();
        }

        // Get the object from sendMessage before sending it
        if(sendingMessage)
            messageRestController.sendMessage(joke.toString());

        return ResponseEntity.ok().body(joke);
    }

    @GetMapping("/getJokeByTitle")
    public ResponseEntity<Joke> getJokeByTitle(
            @RequestParam (name = "sendingMessage", required = false, defaultValue = "false") boolean sendingMessage,
            @RequestParam String title
    ){
        // Pick the good joke and send it to the client
        Joke joke = new Joke();
        for(Joke j : listJokes){
            if(j.getTitle().equalsIgnoreCase(title)){
                joke = j;
            }
        }

        // Means it didn"t gave any joke
        if(joke.getText() == null){
            return ResponseEntity.badRequest().build();
        }

        // Get the object from sendMessage before sending it
        if(sendingMessage)
            messageRestController.sendMessage(joke.toString());

        return ResponseEntity.ok().body(joke);
    }

    @GetMapping("/getJokeLevel")
    public ResponseEntity<Joke> getJokeLevel(
            @RequestParam (name = "sendingMessage", required = false, defaultValue = "false") boolean sendingMessage,
            @RequestParam String level
    ){
        int MIN_RATE = 1;
        int MAX_RATE = 0;

        if(level.equalsIgnoreCase("nulle")){
            MAX_RATE = 5;
        }else if(level.equalsIgnoreCase("bonne")){
            MIN_RATE = 5;
            MAX_RATE = 10;
        }
        
        ArrayList<Joke> sortedListJokes = new ArrayList<>();
        for (Joke j:listJokes) {
            if(j.getNote() <= MAX_RATE && j.getNote() >= MIN_RATE)
                sortedListJokes.add(j);
        }

        // Pick a joke and send it to the client
        Collections.shuffle(sortedListJokes);
        Joke joke = sortedListJokes.get(0);

        // Get the object from sendMessage before sending it
        if(sendingMessage)
            messageRestController.sendMessage(joke.toString());

        return ResponseEntity.ok().body(joke);
    }
}
