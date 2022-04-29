package homework.covidincidence.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import homework.covidincidence.Cache;
import homework.covidincidence.CustomHttpClient;

import org.apache.http.client.utils.URIBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

@Service
public class CovidIncidenceService {

    private static final Logger LOGGER = LogManager.getLogger(CovidIncidenceService.class);

    static final String BASE_URL = "https://covid-193.p.rapidapi.com/";

    private CustomHttpClient client = new CustomHttpClient();

    Cache cache = new Cache(5, 6);

    private static final String ERROR_MSG = "Error!, Please try again";

    public ResponseEntity<String>  getCountries() throws IOException {
        try {
            if(cache.get("countries") != null) {
                LOGGER.debug("GET");
                LOGGER.info("Service: Get countries from cache");

                return new ResponseEntity<>(cache.get("countries"), HttpStatus.OK);
            }
            HttpResponse<String> response = client.getHttpResponse(URI.create(BASE_URL + "countries"));
            LOGGER.info("Service: Get countries from API and Put in cache");
            cache.put("countries", response.body());
            LOGGER.debug("PUT");

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (InterruptedException e){
            LOGGER.error("Service: Get Countries");
            Thread.currentThread().interrupt();

            return new ResponseEntity<>(ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String>  getStatistics(String country) throws URISyntaxException, IOException {
        try {
            URIBuilder uriBuilder = new URIBuilder(BASE_URL + "statistics");

            if (country != null) {
                uriBuilder.addParameter("country", country);
            }
            URI uri = uriBuilder.build();
            HttpResponse<String> response = client.getHttpResponse(uri);
            if(country!= null) {
                LOGGER.info(String.format("Service: Get Statistics of %s from API", country));
            } else {
                LOGGER.info("Service: Get Statistics from API");
            }

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (InterruptedException e){
            LOGGER.error("Service: Get Statistics");
            Thread.currentThread().interrupt();
            
            return new ResponseEntity<>(ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String>  getCountryHistory(String country, String day) throws URISyntaxException, IOException {
        try {
            URIBuilder uriBuilder = new URIBuilder(BASE_URL + "history")
                .addParameter("country", country);

            if (day != null) {
                uriBuilder.addParameter("day", day);
            }
            URI uri = uriBuilder.build();
            HttpResponse<String> response = client.getHttpResponse(uri);

            if(day != null) {
                LOGGER.info(String.format("Service: Get History of %s on the %s from API", country, day));
            } else {
                LOGGER.info("Service: Get History of %s from API", country);
            }

            return new ResponseEntity<>(response.body(), HttpStatus.OK);
        } catch (InterruptedException e){
            LOGGER.error("Service: Get History");
            Thread.currentThread().interrupt();

            return new ResponseEntity<>(ERROR_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<String> getCacheUsageStats() {
        String info = cache.getCacheInfo();
        
        JSONObject json = new JSONObject();
        json.put("response", info);

        return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    }
}
