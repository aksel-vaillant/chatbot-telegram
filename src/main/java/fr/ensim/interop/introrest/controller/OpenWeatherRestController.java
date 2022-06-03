package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.weather.City;
import fr.ensim.interop.introrest.model.weather.OpenWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
@RestController
public class OpenWeatherRestController {

    @Autowired
    private MessageRestController messageRestController;

    @Value("${open.weather.api.url}")
    private String openWeatherUrl;

    @Value("${open.weatherCity.api.url}")
    private String openWeatherCityUrl;

    @Value("${open.weather.api.token}")
    private String openWeatherToken;

    @GetMapping("/getPosition")
    public ResponseEntity<City> getPosition(
            @RequestParam String ville
    ) throws UnsupportedEncodingException {
        String urlRequest = openWeatherCityUrl + "?q=" + URLEncoder.encode(ville, "UTF-8") + "&limit=3" + "&appid=" + openWeatherToken;
        System.out.println(urlRequest);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<City[]> getPosition = restTemplate.getForEntity(urlRequest,
                City[].class, ville);

        City city;
        if(getPosition.getBody().length > 0){
            // Take the first city from the answer
            city = getPosition.getBody()[0];
            return ResponseEntity.ok().body(city);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/meteo")
    public ResponseEntity<OpenWeather> meteo(
            @RequestParam (name = "sendingMessage", required = false, defaultValue = "false") boolean sendingMessage,
            @RequestParam String ville
    ) throws UnsupportedEncodingException {

        City city = getPosition(ville).getBody();

        if(city == null){
            messageRestController.sendMessage("Nous sommes désolés, nous n'avons pas réussi à trouver la météo correspondant à votre ville - " + ville);
            return ResponseEntity.badRequest().build();
        }else{
            String urlRequest = openWeatherUrl + "?lat=" + city.getLat() + "&lon=" + city.getLon()
                    + "&appid=" + openWeatherToken
                    + "&units=metric&lang=fr";

            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<OpenWeather> openWeather = restTemplate.getForEntity(urlRequest,
                    OpenWeather.class);

            OpenWeather weatherObject = openWeather.getBody();

            if(sendingMessage)
                messageRestController.sendMessage("Météo du jour à " + city.getName() + ":\n" +
                    "Temps~ " + weatherObject.getWeather().get(0).getDescription() + "\n"+
                    "Température~ " + weatherObject.getMain().getFeels_like() + "°C" + "\n" +
                     "http://openweathermap.org/img/wn/" + weatherObject.getWeather().get(0).getIcon() + "@4x.png");

            return ResponseEntity.ok().body(weatherObject);
        }
    }
}
