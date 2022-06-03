package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.joke.Joke;
import fr.ensim.interop.introrest.model.joke.ListJoke;
import fr.ensim.interop.introrest.model.telegram.ApiResponseTelegram;
import fr.ensim.interop.introrest.model.telegram.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.MessageSend;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;

@RestController
public class MessageRestController {
	
	@Value("${telegram.api.url}")
	private String telegramApiUrl;

	@Value("${telegram.bot.id}")
	private String telegramBotToken;

	//Opérations sur la ressource getUpdates
	@GetMapping("/getUpdates")
	public ResponseEntity<ApiResponseUpdateTelegram> getUpdates() {
		String urlRequest = telegramApiUrl + telegramBotToken + "/getUpdates?offset=-1";

		RestTemplate restTemplate = new RestTemplate();

		ApiResponseUpdateTelegram getUpdates = restTemplate.getForObject(urlRequest,
				ApiResponseUpdateTelegram.class);

		return ResponseEntity.ok().body(getUpdates);
	}

	//Opérations sur la ressource sendMessage
	@GetMapping("/sendMessage")
	public ResponseEntity<ApiResponseTelegram> sendMessage(
			@RequestParam String message) {

		MessageSend messageToSend = new MessageSend(message);
		messageToSend.setChat_id("935926668");

		String urlRequest = telegramApiUrl + telegramBotToken + "/sendMessage";

		RestTemplate restTemplate = new RestTemplate();
		ApiResponseTelegram sendMessage = restTemplate.postForObject(urlRequest,
				messageToSend, ApiResponseTelegram.class);

		return ResponseEntity.ok().body(sendMessage);
	}
}
