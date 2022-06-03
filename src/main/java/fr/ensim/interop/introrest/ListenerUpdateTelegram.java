package fr.ensim.interop.introrest;

import fr.ensim.interop.introrest.controller.JokeRestController;
import fr.ensim.interop.introrest.controller.MessageRestController;
import fr.ensim.interop.introrest.controller.OpenWeatherRestController;
import fr.ensim.interop.introrest.model.telegram.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.Message;
import fr.ensim.interop.introrest.model.telegram.Update;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ListenerUpdateTelegram implements CommandLineRunner {

	@Autowired
	private MessageRestController messageRestController;
	@Autowired
	private OpenWeatherRestController openWeatherRestController;
	@Autowired
	private JokeRestController jokeRestController;

	private int savedLastID = 0;

	@Override
	public void run(String... args) throws Exception {
		Logger.getLogger("ListenerUpdateTelegram").log(Level.INFO, "Démarage du listener d'updates Telegram...");

		// Registered the last update id
		savedLastID = messageRestController.getUpdates().getBody().getResult().get(0).getUpdateId();

		// Operation de pooling pour capter les evenements Telegram
		TimerTask task = new TimerTask() {
			@SneakyThrows
			public void run() {
				System.out.println("Task performed on: " + new Date());

				// Return the last object with the offset -1
				ResponseEntity<ApiResponseUpdateTelegram> responseUpdates = messageRestController.getUpdates();
				Update lastUpdate = responseUpdates.getBody().getResult().get(0);

				String lastMessageFromClient = lastUpdate.getMessage().getText();
				int lastIDMessageFromClient = lastUpdate.getUpdateId();

				// Check if the savedLastID is different form the new update
				if(savedLastID != lastIDMessageFromClient){

					String[] cmds = lastMessageFromClient.split(" ");

					if(cmds[0].equalsIgnoreCase("blague") || cmds[0].equalsIgnoreCase("joke")){
						System.out.println(lastMessageFromClient);
						System.out.println(cmds.length);

						if(cmds.length == 1){
							jokeRestController.getJoke(true);
						}else{
							jokeRestController.getJokeLevel(true, cmds[1]);
						}
					}
					else if(cmds[0].equalsIgnoreCase("meteo") || cmds[0].equalsIgnoreCase("weather")){
						if(cmds.length == 1){
							openWeatherRestController.meteo(true, "Le Mans");
						}else{
							// To get cities with more than a word
							String cityName = lastMessageFromClient.replaceAll(cmds[0], "");
							openWeatherRestController.meteo(true, cityName);
						}
					}
					else if(cmds[0].equalsIgnoreCase("aide") || cmds[0].equalsIgnoreCase("help")){
						messageRestController.sendMessage("Voici la liste des commandes!\n" +
								"blague\t avoir une blague\n" +
								"blague nulle\t avoir une blague nulle\n" +
								"blague bonne\t avoir une bonne blague\n" +
								"meteo ville\t avoir la meteo d'une ville\n" +
								"\t - par défaut : Le Mans");
					}
					else{
						messageRestController.sendMessage("Désolé, nous n'avons pas compris votre requête! " +
								"Vous pouvez voir l'ensemble des commandes avec aide ou help ;)");
					}



					savedLastID = lastIDMessageFromClient;
				}

			}
		};
		Timer timer = new Timer("Timer");

		long delay = 1000;
		timer.schedule(task, delay,5000);
	}
}
