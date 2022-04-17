package homework.covidincidence.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import homework.covidincidence.Cache;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@Service
public class CovidIncidenceService {

    private static final Logger LOGGER = LogManager.getLogger(CovidIncidenceService.class);

    final String baseURL = "https://covid-193.p.rapidapi.com/";

    final String host = "covid-193.p.rapidapi.com";

    final String key = "e43a5d1d6fmsh454b6ef0f1e4428p104c4fjsnd533eb647f22";

    Cache cache = new Cache(5, 6);

    public ResponseEntity<String>  getCountries() {
        try {
            if(cache.get("countries") != null) {
                LOGGER.debug("GET");
                LOGGER.info("Service: Get countries from cache");
                return new ResponseEntity<>((String)cache.get("countries"), HttpStatus.OK);
            }
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseURL + "countries"))
                .header("X-RapidAPI-Host", host)
                .header("X-RapidAPI-Key", key)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
                HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
                LOGGER.info("Service: Get countries from API");
                cache.put("countries", response.body());
                LOGGER.debug("PUT");
            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Service: Get Countries");
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String>  getStatistics(String country) {
        try {
            URIBuilder uriBuilder = new URIBuilder(baseURL + "statistics");

            if (country != null) {
                uriBuilder.addParameter("country", country);
            }
            URI uri = uriBuilder.build();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("X-RapidAPI-Host", host)
                .header("X-RapidAPI-Key", key)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info(country != null ? "Service: Get Statistics of " + country + "  from API" :  "Service: Get Statistics from API");
            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Service: Get Statistics");
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String>  getCountryHistory(String country, String day) {
        try {
            URIBuilder uriBuilder = new URIBuilder(baseURL + "history")
                .addParameter("country", country);

            if (country != null) {
                uriBuilder.addParameter("day", day);
            }
            URI uri = uriBuilder.build();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("X-RapidAPI-Host", host)
                .header("X-RapidAPI-Key", key)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            LOGGER.info(day != null ? "Service: Get History of " + country + " on the " + day + " from API" :  "Service: Get History of " + country + " from API");
            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Service: Get History");
            return new ResponseEntity<>("Error!, Please try again", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
